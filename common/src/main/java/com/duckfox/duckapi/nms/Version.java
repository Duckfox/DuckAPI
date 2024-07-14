package com.duckfox.duckapi.nms;

import org.bukkit.Bukkit;

public enum Version {
    UNKNOWN("Unknown", ""),
    V1_12_2("1122", "v1_12_R1"),
    V1_16_5("1165", "v1_16_R3"),
    V1_20_2("1202","v1_20_R2");

    public final String versionName;
    public final String nmsName;

    Version(String versionName, String nmsName) {
        this.versionName = versionName;
        this.nmsName = nmsName;
    }

    private static Version serverVersion;

    private static Version getVersion(String version) {
        for (Version value : values()) {
            if (value.versionName.equalsIgnoreCase(version)) {
                return value;
            }
        }
        return UNKNOWN;
    }

    public boolean isVersion(Version version) {
        return this == version;
    }

    public boolean isHigher(Version version) {
        return version.ordinal() > this.ordinal();
    }

    public boolean isLower(Version version) {
        return version.ordinal() < this.ordinal();
    }

    public static Version getVersion() {
        if (serverVersion != null) {
            return serverVersion;
        }
        String version = Bukkit.getServer().getVersion().split("MC: ")[1].replace(")", "").replace(".","");
        Version version1 = getVersion(version);
        if (version1 != null) {
            serverVersion = version1;
        }
        return version1;
    }
}
