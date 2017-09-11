package some.weird.ppafirebaselogin2017.Firebase;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


/**
 * Created by artur on 11.09.2017.
 */

public class FirebaseStorageHelper {

    private static final String TAG = FirebaseStorageHelper.class.getCanonicalName();
    private FirebaseStorage firebaseStorage;
    private StorageReference rootRef;
    private Context context;

    public FirebaseStorageHelper(Context context){
        this.context = context;
        rootRef = firebaseStorage.getReferenceFromUrl("veare.org");
    }

    public void saveProfileImageToCloud(String userId, Uri selectedImageUri, final ImageView imageView) {

        StorageReference photoParentRef = rootRef.child(userId);
        StorageReference photoRef = photoParentRef.child(selectedImageUri.getLastPathSegment());
        UploadTask uploadTask = photoRef.putFile(selectedImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "OnFailure " + e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Glide.with(context).load(downloadUrl.getPath()).into(imageView);
            }
        });
    }
}
