package tech.qijin.sdk.tencent.cloud.base;

public enum COSScene {
    FILE,
    IMG,
    AUDIO,
    VIDEO;

    public String str() {
        return this.name().toLowerCase();
    }
}
