package activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wasu.winton.phonetv.R;
import com.winton.player.widget.media.AndroidMediaController;
import com.winton.player.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016/4/15.
 */
public class PlayerActivity extends WstvBaseActivity  {
    private static final String TAG = "PlayerActivity";

    private AndroidMediaController mediaController = null;
    private IjkVideoView mVideoView = null;
    private TextView mToastTextView = null;
    private AndroidMediaController mMediaController = null;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;



    private TextView mTVPath = null;

    private String mVideoPath = null;
    private Uri mVideoUri = null;

    private boolean mBackPressed = false;

    public static Intent newIntent(Context context, String videoPath, String videoTitle) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("videoPath", videoPath);
        intent.putExtra("videoTitle", videoTitle);
        return intent;
    }

    public static void intentTo(Context context, String videoPath, String videoTitle) {
        context.startActivity(newIntent(context, videoPath, videoTitle));
    }


    static {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_player);

        mMediaController = new AndroidMediaController(this,false);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setMediaController(mMediaController);
        mTVPath = (TextView)findViewById(R.id.tv_video_path);

           /*初始化ToolBar*/
        mToolBar = (Toolbar)findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_back);

        mTabLayout = (TabLayout)findViewById(R.id.tablayout);
        mTabLayout.setVisibility(View.GONE);

        mVideoView.setVideoPath("storage/emulated/legacy/Download/1.mp4");
        mVideoView.start();

    }

    @Override
    public void initListener() {
        mTVPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri uri = data.getData();
        mTVPath.setText(uri.getPath());
        mVideoView.setVideoPath(uri.getPath());
        mVideoView.start();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
