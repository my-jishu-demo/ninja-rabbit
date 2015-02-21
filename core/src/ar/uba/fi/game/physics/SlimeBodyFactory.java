package ar.uba.fi.game.physics;

import ar.uba.fi.game.NinjaRabbitGame;
import ar.uba.fi.game.entity.Direction;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author nfantone
 *
 */
public final class SlimeBodyFactory implements BodyFactory {
	private static final float SLIME_SCALE = 30 / NinjaRabbitGame.PPM;

	private final BodyEditorLoader loader;
	private final BodyDef bdef;
	private final FixtureDef fdef;

	public SlimeBodyFactory(final BodyEditorLoader loader) {
		if (loader == null) {
			throw new IllegalArgumentException("'loader' cannot be null");
		}
		this.loader = loader;

		bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(0, 0);
		bdef.fixedRotation = true;

		fdef = new FixtureDef();
		fdef.density = 0.7f;
		fdef.restitution = 0.1f;
		fdef.friction = 0.6f;
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.uba.fi.game.physics.BodyFactory#create(com.badlogic.gdx.physics.box2d.World,
	 * ar.uba.fi.game.entity.Direction)
	 */
	@Override
	public Body create(final World world, final Direction direction) {
		return create(world, bdef, direction);
	}

	@Override
	public Body create(final World world, final BodyDef definition) {
		return create(world, definition, null);
	}

	@Override
	public Body create(final World world, final BodyDef definition, final Direction direction) {
		Body slimeBody = world.createBody(definition);
		loader.attachFixture(slimeBody, SlimePhysicsProcessor.SLIME_IDENTIFIER, fdef, SLIME_SCALE);
		slimeBody.getFixtureList().first().setUserData(SlimePhysicsProcessor.SLIME_IDENTIFIER);
		return slimeBody;
	}
}
