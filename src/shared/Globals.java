package shared;

public interface Globals {
	public static enum PTStatus {
		UPTODATE,
		OLD,
		NEW,
		MISSING,
		MIXED,
		UPDATING,
		UPLOADING,
		DELETEDONSERVER
	}
	
	public static final long PATH_DELETED_ON_SERVER = -1;
	
	public static final byte FILE = 0;
	public static final byte FOLDER = 0;
}
