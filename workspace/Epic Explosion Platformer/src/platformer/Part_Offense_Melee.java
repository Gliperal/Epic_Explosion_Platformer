package platformer;

public abstract class Part_Offense_Melee {
	private CollidableArea[] hitboxWhenAttacking;	// array in index is frame in animation
	private CollidableArea hitboxWhenNotAttacking;
	private int attackingFrame = -1;

	public Part_Offense_Melee(CollidableArea[] attackingHitbox, CollidableArea nonAttackingHitbox) {
		hitboxWhenAttacking = attackingHitbox;
		hitboxWhenNotAttacking = nonAttackingHitbox;
	}

	public void onUpdate() {
		if(attackingFrame != -1) {
			attackingFrame++;	// Move to next attack animation frame
			if(attackingFrame > hitboxWhenAttacking.length) {	// End of animaiton
				attackingFrame = -1;
			}
		}
	}

	public void attack() {
		if(attackingFrame == -1) {
			attackingFrame = 0;
		}
	}
	
	public Collidable getAttackArea() {
		if(attackingFrame == -1) return hitboxWhenNotAttacking;
		return hitboxWhenAttacking[attackingFrame];
	}
}
