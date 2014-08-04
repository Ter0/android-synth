package net.ter0.synth;

public class TriangleSynth extends Synth {

    public TriangleSynth(double frequency) {
        super(frequency, "Triangle");
    }

    @Override
    protected void generateSamples(short[] samples) {
        double phase = getPhase();
        for (int i = 0; i < getBufferSize(); i++) {
            samples[i] = (short) ((2 / Math.PI) * Math.asin(Math.sin(phase)) * Synth.AMPLITUDE);
            phase += (Math.PI * 2) * (getFrequency() / Synth.SAMPLE_RATE);
        }
        setPhase(phase);
    }
}
