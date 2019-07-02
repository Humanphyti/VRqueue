
public abstract class Players {

	protected String name;
	protected String game;
	
	public Players() {
		name = "name";
		game = "game";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getGame() {
		return game;
	}
	
	public String toString() {
		return String.format("%s", name);
	}
	
}
