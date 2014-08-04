package net.ter0.synth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SynthAdapter extends ArrayAdapter<Synth> {

    public SynthAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.synth_item, parent, false);
        }

        final Synth synth = getItem(position);

        ((TextView) convertView.findViewById(R.id.synth_label)).setText(synth.getLabel());
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        synth.start();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        synth.stop();
                        return true;
                    default:
                        return false;
                }
            }
        });

        return convertView;
    }

    public void destroyAll() {
        for (int i = 0; i < getCount(); i++) {
            getItem(i).destroy();
        }
    }
}
