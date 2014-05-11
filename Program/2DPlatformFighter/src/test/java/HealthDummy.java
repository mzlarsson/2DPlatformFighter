import edu.chalmers.brawlbuddies.model.skills.DamageAble;
import edu.chalmers.brawlbuddies.model.world.Health;

/**
 * A class for testing damage Effect.
 * @author David Gustafsson
 *
 */
public class HealthDummy implements DamageAble {
	private Health health;
	private int ID;
	public HealthDummy(float a){
		health = new Health(a);
		
	}
	public Health getHealth(){
		return health;
	}
	public void takeDamage(float a) {
		health.takeDamage(a);
		
	}
	public void setID(int a){
		ID = a;
	}
	
	public int getID() {
		return ID;
	}

}
