package snsoftware.veegeeiptv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

import snsoftware.veegeeiptv.Network_Operations.get_episodes_of_seasons;


public class Season_episode_page extends AppCompatActivity implements IVLCVout.Callback {
    ListView episode_list;
    SharedPreferences prefs;
    SurfaceView preview;
    String url=null;
    DisplayMetrics displayMetrics;
    LibVLC libvlc;
    MediaPlayer mediaPlayer;
    long current_position;
    SurfaceHolder holder;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_episode_page);
        ll=findViewById(R.id.root_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        episode_list=findViewById(R.id.episode_list);
        preview=findViewById(R.id.preview);
        holder=preview.getHolder();
         displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width=displayMetrics.widthPixels;
        params.height=displayMetrics.heightPixels/3;
        ll.getChildAt(0).setLayoutParams(params);
            FrameLayout video_frame=findViewById(R.id.video_frame);
            video_frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current_position = mediaPlayer.getTime();
                      releasePlayer();
                    Intent intent = new Intent(Season_episode_page.this, JavaActivity.class);
                    intent.putExtra("channel_url", url);
                    intent.putExtra("current_position", current_position);
                    startActivity(intent);
                }
            });


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("episode_stream_link"));
        LocalBroadcastManager.getInstance(this).registerReceiver(first_episode_reciever,
                new IntentFilter("first_episode_stream"));
        new get_episodes_of_seasons(Season_episode_page.this,
                episode_list,prefs.getString("username",""),
                prefs.getString("password","")
                ,getIntent().getStringExtra("series_id")
                ,getIntent().getStringExtra("season_id")).Call_Api();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(url!=null)
            play_video(url);
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            url=intent.getStringExtra("episode_stream_link");
            play_video(intent.getStringExtra("episode_stream_link"));
        }
    };
    public BroadcastReceiver first_episode_reciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            url=intent.getStringExtra("first_episode_stream");
            play_video(intent.getStringExtra("first_episode_stream"));
        }
    };
    private void play_video(String url) {
        releasePlayer();
        ArrayList<String> options = new ArrayList();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch");
        options.add("-vvv");
        this.libvlc = new LibVLC(this, options);
        this.mediaPlayer = new MediaPlayer(this.libvlc);
        IVLCVout vout = this.mediaPlayer.getVLCVout();
        vout.setVideoView(this.preview);
        vout.setWindowSize(displayMetrics.widthPixels, displayMetrics.heightPixels/3);
        vout.addCallback(this);
        vout.attachViews();
        Media m = new Media(this.libvlc, Uri.parse(url));
        m.setHWDecoderEnabled(true, false);
        m.addOption(":network-caching=100");
        m.addOption(":clock-jitter=0");
        m.addOption(":clock-synchro=0");
        m.addOption(":fullscreen");
        this.mediaPlayer.setMedia(m);
        this.mediaPlayer.setAspectRatio("16:9");
        this.mediaPlayer.play();
    }

    private void releasePlayer() {
        if (this.libvlc != null) {
            this.mediaPlayer.stop();
            IVLCVout vout = this.mediaPlayer.getVLCVout();
            vout.removeCallback(this);
            vout.detachViews();
            this.holder = null;
            this.libvlc.release();
            this.libvlc = null;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

   /*
    public void play_video(String url){
        if(preview.isPlaying()){
            preview.stopPlayback();
        }
            preview.setVideoURI(Uri.parse(url));
            preview.start();
    }
   */

    @Override
    public void onSurfacesCreated(IVLCVout ivlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout ivlcVout) {

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            param.height=displayMetrics.heightPixels/3;
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            ll.getChildAt(0).setLayoutParams(param);
            ll.getChildAt(1).setLayoutParams(param2);
            ll.setOrientation(LinearLayout.VERTICAL);
        }else if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            ll.setWeightSum(2);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
            );
            param.width=0;
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            param2.width=0;
            param2.gravity=Gravity.CENTER_HORIZONTAL;
            param2.height=600;
            param2.topMargin=10;
            param2.leftMargin=10;
            param2.rightMargin=10;
            param2.bottomMargin=10;
            ll.getChildAt(0).setLayoutParams(param2);
            ll.getChildAt(1).setLayoutParams(param);
            ll.setOrientation(LinearLayout.HORIZONTAL);
        }
    }
}
