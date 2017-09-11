package some.weird.ppafirebaselogin2017;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import some.weird.ppafirebaselogin2017.Firebase.FirebaseDatabaseHelper;
import some.weird.ppafirebaselogin2017.Firebase.FirebaseStorageHelper;
import some.weird.ppafirebaselogin2017.Helper.Helper;
import some.weird.ppafirebaselogin2017.Helper.SimpleDividerItemDecoration;

/**
 * Created by artur on 05.09.2017.
 */

public class ProfileFragment extends Fragment {
    private static final String TAG = ProfileFragment.class.getSimpleName();
    private ImageView profilePhoto;
    private TextView profileName;
    private TextView country;
    private TextView userStatus;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private String id;
    private static final int REQUEST_READ_PERMISSION = 120;

    public ProfileFragment(){

    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("My Profile");

        profileName = (TextView) view.findViewById(R.id.profile_name);
        country = (TextView) view.findViewById(R.id.country);
        profileName.setVisibility(View.GONE);
        country.setVisibility(View.GONE);

        profilePhoto = (ImageView) view.findViewById(R.id.circleView);
        profilePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Helper.SELECT_PICTURE);
            }
        });
        recyclerView = (RecyclerView)view.findViewById(R.id.profile_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        ((FirebaseApplication)getActivity().getApplication()).getFirebaseAuth();
        id = ((FirebaseApplication)getActivity().getApplication()).getFirebaseUserAuthenticateId();
        FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        firebaseDatabaseHelper.isUserKeyExist(id, getActivity(), recyclerView);
        return view;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.edit_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_edit_profile){
            Intent editProfileIntent = new Intent(getActivity(), EditProfileActivity.class);
            getActivity().startActivity(editProfileIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("user id has entered onActivityResult ");
        if (requestCode == Helper.SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            String imagePath = getPath(selectedImageUri);
            FirebaseStorageHelper storageHelper = new FirebaseStorageHelper(getActivity());

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_PERMISSION);
                }
                return;
            }
            storageHelper.saveProfileImageToCloud(id, selectedImageUri, profilePhoto);
        }
    }

    public String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        assert cursor != null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    @Override
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getActivity(), "Sorry!!!, you can't use this app without granting this permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}
