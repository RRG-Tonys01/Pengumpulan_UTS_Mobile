package id.ac.umn.uts_40075.model;

import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;

public class Library implements Parcelable {
    private String id;
    private String title;
    private String subTitle;
    private int sound;
    private MediaPlayer mediaPlayer;
    private boolean isPlay = false;

    public Library() {

    }

    protected Library(Parcel in) {
        id = in.readString();
        title = in.readString();
        subTitle = in.readString();
        sound = in.readInt();
        isPlay = in.readByte() != 0;
    }

    public static final Creator<Library> CREATOR = new Creator<Library>() {
        @Override
        public Library createFromParcel(Parcel in) {
            return new Library(in);
        }

        @Override
        public Library[] newArray(int size) {
            return new Library[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public boolean isPlay() {
        return !isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeInt(sound);
        parcel.writeByte((byte) (isPlay ? 1 : 0));
    }
}
