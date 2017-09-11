package some.weird.ppafirebaselogin2017;

/**
 * Created by artur on 05.09.2017.
 */

public class UserProfile {
    private String header;
    private String profileContent;

    public UserProfile(String header, String profileContent){
        this.header = header;
        this.profileContent = profileContent;
    }

    public String getHeader() { return header; }
    public String getProfileContent() { return profileContent; }

}
