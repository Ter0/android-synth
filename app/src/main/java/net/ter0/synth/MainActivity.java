package net.ter0.synth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity {

    private Synth mSinSynth;
    private Synth mSquareSynth;
    private Synth mTriangleSynth;
    private Synth mSawtoothSynth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSinSynth = new SinSynth(263.74);
        mSquareSynth = new SquareSynth(263.74);
        mSawtoothSynth = new SawtoothSynth(263.74);
        mTriangleSynth = new TriangleSynth(263.74);

        findViewById(R.id.sawtooth).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mSawtoothSynth.start();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mSawtoothSynth.stop();
                        return true;
                    default:
                        return false;
                }
            }
        });

        findViewById(R.id.triangle).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mTriangleSynth.start();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mTriangleSynth.stop();
                        return true;
                    default:
                        return false;
                }
            }
        });
        findViewById(R.id.sin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mSinSynth.start();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mSinSynth.stop();
                        return true;
                    default:
                        return false;
                }
            }
        });

        findViewById(R.id.square).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mSquareSynth.start();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        mSquareSynth.stop();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSinSynth.destroy();
        mSquareSynth.destroy();
        mTriangleSynth.destroy();
        mSawtoothSynth.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
