package highwin.zgs.gallerysnow;

public class Photo {

    int position;
    boolean zoom;

    public Photo(int position, boolean zoom) {
        this.position = position;
        this.zoom = zoom;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }
}
