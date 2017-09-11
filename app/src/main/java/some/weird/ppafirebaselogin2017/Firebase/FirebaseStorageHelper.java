package some.weird.ppafirebaselogin2017.Firebase;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;



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
        rootRef = firebaseStorage.getReferenceFromUrl("gs://fir-analyticexample.appspot.com");
    }

    public void saveProfileImageToCloud(String userId, Uri selectedImageUri, final ImageView imageView) {

        StorageReference photoParentRef = rootRef.child(userId);
        StorageReference photoRef = photoParentRef.child(selectedImageUri.getLastPathSegment());
        UploadTask uploadTask = photoRef.putFile(selectedImageUri);


    }
}
