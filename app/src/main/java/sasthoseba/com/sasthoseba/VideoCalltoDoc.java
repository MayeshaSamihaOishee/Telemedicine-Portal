package sasthoseba.com.sasthoseba;
import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

import java.util.ArrayList;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import sasthoseba.com.sasthoseba.R;


public class VideoCalltoDoc extends AppCompatActivity implements  Session.SessionListener,
        PublisherKit.PublisherListener {
     private static String API_KEY = "46159782";
   // private static String API_KEY = null;
      private static String SESSION_ID ="2_MX40NjE1OTc4Mn5-MTUzMjUxMzE0ODQzM356Zk1OR0dHTWoyTmNHV1lLdXpqWjY0UUN-fg";
   // private static String SESSION_ID = null;
      private static String TOKEN = "T1==cGFydG5lcl9pZD00NjE1OTc4MiZzaWc9YTYyNTVjYzk1NDhmOTIzOTY4Yzg2YjEzYmJlZTVmMzk5NThiMWJlODpzZXNzaW9uX2lkPTJfTVg0ME5qRTFPVGM0TW41LU1UVXpNalV4TXpFME9EUXpNMzU2WmsxT1IwZEhUV295VG1OSFYxbExkWHBxV2pZMFVVTi1mZyZjcmVhdGVfdGltZT0xNTMyNTEzMTg3Jm5vbmNlPTAuNTczMTY3MzQxNjk5NDc5MSZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNTM1MTA1MTgzJmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
   // private static String TOKEN = null;
    private static final String LOG_TAG = VideoCalltoDoc.class.getSimpleName();
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;

    private Session mSession;
    private FrameLayout mPublisherViewContainer;
    private FrameLayout mSubscriberViewContainer;


    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

  /* public void fetchSessionConnectionData() {
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(new JsonObjectRequest(Request.Method.GET,

                "https://projectapikey.herokuapp.com" + "/session" ,

                // "https://ehealthtrial.herokuapp.com" + "session",
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    API_KEY = response.getString("apiKey");
                    SESSION_ID = response.getString("sessionId");
                    TOKEN = response.getString("token");

                    Log.i(LOG_TAG, "API_KEY: " + API_KEY);
                    Log.i(LOG_TAG, "SESSION_ID: " + SESSION_ID);
                    Log.i(LOG_TAG, "TOKEN: " + TOKEN);

                    mSession = new Session.Builder(VideoCalltoDoc.this, API_KEY, SESSION_ID).build();
                    mSession.setSessionListener(VideoCalltoDoc.this);
                    mSession.connect(TOKEN);


                    //p2p.preference="enabled";

                } catch (JSONException error) {
                    Log.e(LOG_TAG, "Web Service error: " + error.getMessage());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Web Service error: " + error.getMessage());
            }
        }));
    }  */


  /*  public void fetchSessionConnectionData() {
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        reqQueue.add(new JsonObjectRequest(Request.Method.GET,

                "https://projectapikey.herokuapp.com" + "/session" ,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    API_KEY = response.getString("apiKey");
                    SESSION_ID = response.getString("sessionId");
                    TOKEN = response.getString("token");

                    Log.i(LOG_TAG, "API_KEY: " + API_KEY);
                    Log.i(LOG_TAG, "SESSION_ID: " + SESSION_ID);
                    Log.i(LOG_TAG, "TOKEN: " + TOKEN);

                    mSession = new Session.Builder(VideoCalltoDoc.this, API_KEY, SESSION_ID).build();
                    mSession.setSessionListener(VideoCalltoDoc.this);
                    mSession.connect(TOKEN);


                    //p2p.preference="enabled";

                } catch (JSONException error) {
                    Log.e(LOG_TAG, "Web Service error: " + error.getMessage());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Web Service error: " + error.getMessage());
            }
        }));
    }
*/

    private Publisher mPublisher;
    private Subscriber mSubscriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_callto_doc);
        requestPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // initialize view objects from your layout
            mPublisherViewContainer = (FrameLayout) findViewById(R.id.publisher_container);
            mSubscriberViewContainer = (FrameLayout) findViewById(R.id.subscriber_container);

            // initialize and connect to the session
            mSession = new Session.Builder(this, API_KEY, SESSION_ID).build();
            mSession.setSessionListener(this);
            mSession.connect(TOKEN);


        } else {
            EasyPermissions.requestPermissions(this, "This app needs access to your camera and mic to make video calls", RC_VIDEO_APP_PERM, perms);
        }
    }


   /* @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {
        String[] perms = { Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // initialize view objects from your layout
            mPublisherViewContainer = (FrameLayout) findViewById(R.id.publisher_container);
            mSubscriberViewContainer = (FrameLayout) findViewById(R.id.subscriber_container);

            // initialize and connect to the session
           // fetchSessionConnectionData();

        } else {
            EasyPermissions.requestPermissions(this, "This app needs access to your camera and mic to make video calls", RC_VIDEO_APP_PERM, perms);
        }
    }  */





    // SessionListener methods

    @Override
    public void onConnected(Session session) {
        Log.i(LOG_TAG, "Session Connected");

        mPublisher = new Publisher.Builder(this).build();
        mPublisher.setPublisherListener(this);

        mPublisherViewContainer.addView(mPublisher.getView());
        mSession.publish(mPublisher);
    }


    @Override
    public void onDisconnected(Session session) {
        Log.i(LOG_TAG, "Session Disconnected");
    }


    @Override
    public void onStreamReceived(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Received");

        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(this, stream).build();
            mSession.subscribe(mSubscriber);
            mSubscriberViewContainer.addView(mSubscriber.getView());
        }
    }


    @Override
    public void onStreamDropped(Session session, Stream stream) {
        Log.i(LOG_TAG, "Stream Dropped");

        if (mSubscriber != null) {
            mSubscriber = null;
            mSubscriberViewContainer.removeAllViews();
        }
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {
        Log.e(LOG_TAG, "Session error: " + opentokError.getMessage());
    }


    // PublisherListener methods

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamCreated");
    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {
        Log.i(LOG_TAG, "Publisher onStreamDestroyed");
    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {
        Log.e(LOG_TAG, "Publisher error: " + opentokError.getMessage());
    }

    int clickcount=0;

    public void mutemethod(View view) {
        clickcount=clickcount+1;
        if (clickcount%2!=0) {
            mPublisher.setPublishAudio(false);
        }
        else if (clickcount%2==0){
            mPublisher.setPublishAudio(true);
        }
    }

    public void swapcameramethod(View view) {
        mPublisher.swapCamera();
    }

    public void end(View view) {
        mPublisher.setPublishVideo(false);
        mPublisher.setPublishAudio(false);
    }
}
