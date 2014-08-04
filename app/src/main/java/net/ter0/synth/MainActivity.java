package net.ter0.synth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity {

    private SynthAdapter mSynthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSynthAdapter = new SynthAdapter(this, R.layout.synth_item);
        ((ListView) findViewById(R.id.synth_list_view)).setAdapter(mSynthAdapter);
        mSynthAdapter.add(new SawtoothSynth(123));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSynthAdapter.destroyAll();
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
        switch (id) {
            case R.id.action_add_synth:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.add_synth_dialog, null));
                builder.setTitle("Add new Synthesizer");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String type = getResources().getStringArray(R.array.synth_types)[which];
                        double frequency = Double.parseDouble(((EditText) findViewById(R.id.editText)).getText().toString());
                        if (type.equals("sin")) {
                            mSynthAdapter.add(new SinSynth(frequency));
                        } else if (type.equals("sawtooth")) {
                            mSynthAdapter.add(new SawtoothSynth(frequency));
                        } else if (type.equals("triangle")) {
                            mSynthAdapter.add(new TriangleSynth(frequency));
                        } else if (type.equals("square")) {
                            mSynthAdapter.add(new SquareSynth(frequency));
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
