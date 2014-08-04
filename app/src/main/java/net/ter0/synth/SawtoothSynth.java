package net.ter0.synth;

public class SawtoothSynth extends Synth {
    public SawtoothSynth(double frequency) {
        super(frequency, "Sawtooth");
    }

    @Override
    protected void generateSamples(short[] samples) {
        double phase = getPhase();
        for (int i = 0; i < getBufferSize(); i++) {
            samples[i] = (short) ((2 * ((phase / (2 * Math.PI)) - Math.floor(0.5 + (phase / (2 * Math.PI))))) * Synth.AMPLITUDE);
            phase += (Math.PI * 2) * (getFrequency() / Synth.SAMPLE_RATE);
        }
        setPhase(phase);
    }
}
