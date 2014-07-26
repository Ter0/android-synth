package net.ter0.synth;

public class SinSynth extends Synth {

    public SinSynth(double frequency) {
        super(frequency);
    }

    @Override
    protected void generateSamples(short[] samples) {
        double phase = getPhase();
        for (int i = 0; i < getBufferSize(); i++) {
            samples[i] = (short) (Synth.AMPLITUDE * Math.sin(phase));
            phase += (Math.PI * 2) * (getFrequency() / Synth.SAMPLE_RATE);
        }
        setPhase(phase);
    }
}
