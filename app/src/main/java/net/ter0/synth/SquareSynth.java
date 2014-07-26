package net.ter0.synth;

import android.util.Log;

import java.util.Arrays;

public class SquareSynth extends Synth {
    public SquareSynth(double frequency) {
        super(frequency);
    }

    @Override
    protected void generateSamples(short[] samples) {
        double phase = getPhase();
        for (int i = 0; i < getBufferSize(); i++) {
            samples[i] = (short) (Math.signum(Math.sin(phase)) * Synth.AMPLITUDE);
            phase += (Math.PI * 2) * (getFrequency() / Synth.SAMPLE_RATE);
        }
        setPhase(phase);
    }
}
