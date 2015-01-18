package me.cooperka.cafalert;

public class Alert
{
	private String name = "";
	private boolean active = false;

	public void setStuff(String Name, boolean Active) {
		this.name = Name;
		this.active = Active;
	}

	public void setStuff(String Name) {
		this.name = Name;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean Active) {
		this.active = Active;
	}
}
