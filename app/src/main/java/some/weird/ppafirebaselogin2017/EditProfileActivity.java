package some.weird.ppafirebaselogin2017;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by artur on 05.09.2017.
 */

public class EditProfileActivity {

    private static final String TAG = EditProfileActivity.class.getSimpleName();
    private EditText editProfileName;
    private EditText editProfileCountry;
    private EditText editProfilePhoneNumber;
    private EditText editProfileHobby;
    private EditText editProfileBirthday;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

}
