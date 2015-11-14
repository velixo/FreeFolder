package model;

import shared.Globals.PTStatus;

public interface PathTracker {
	public abstract PTStatus getStatus();
	public abstract String getName();
	public abstract long getLastModified();
	public abstract int getId();
	/** Reloads status and attributes based on local and server events*/
	public abstract void update();
	public abstract byte[] encode();
}
