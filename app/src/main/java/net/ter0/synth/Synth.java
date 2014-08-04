package net.ter0.synth;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public abstract class Synth {

    protected static final int SAMPLE_RATE = 44100;
    protected static final int AMPLITUDE = 10000;
    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_OUT_STEREO;
    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private final int mBufferSize;
    private Thread mThread;
    private AudioTrack mAudioTrack;
    private boolean mIsRunning;
    private double mPhase;
    private double mFrequency;
    private String mLabel;

    protected Synth(double frequency, String label) {
        mBufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);
        mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                mBufferSize,
                AudioTrack.MODE_STREAM);
        mLabel = label;
        mFrequency = frequency;
        mPhase = 0.0;
        mThread = new Thread() {
            @Override
            public void run() {
                setPriority(Thread.MAX_PRIORITY);

                short samples[] = new short[mBufferSize];
                mAudioTrack.play();

                while (true) {

                    if (Thread.currentThread().isInterrupted())
                        break;

                    if (mIsRunning) {
                        generateSamples(samples);
                        mAudioTrack.write(samples, 0, mBufferSize);
                    }
                }

                mAudioTrack.stop();
                mAudioTrack.release();
            }
        };
        mIsRunning = false;
        mThread.start();
    }

    public int getBufferSize() {
        return mBufferSize;
    }

    public String getLabel() {
        return mLabel;
    }

    public Thread getThread() {
        return mThread;
    }

    public void setThread(Thread mThread) {
        this.mThread = mThread;
    }

    public AudioTrack getAudioTrack() {
        return mAudioTrack;
    }

    public void setAudioTrack(AudioTrack mAudioTrack) {
        this.mAudioTrack = mAudioTrack;
    }

    public boolean isIsRunning() {
        return mIsRunning;
    }

    public void setIsRunning(boolean mIsRunning) {
        this.mIsRunning = mIsRunning;
    }

    public double getPhase() {
        return mPhase;
    }

    public void setPhase(double mPhase) {
        this.mPhase = mPhase;
    }

    public double getFrequency() {
        return mFrequency;
    }

    public void setFrequency(double mFrequency) {
        this.mFrequency = mFrequency;
    }

    protected abstract void generateSamples(short[] samples);

    public void start() {
        mIsRunning = true;
    }

    public void stop() {
        mIsRunning = false;
        setPhase(0.0);
    }

    public void destroy() {
        mThread.interrupt();
    }
}
