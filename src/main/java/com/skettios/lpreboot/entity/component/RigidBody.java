package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.math.Rectangle;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.util.Constants;

public class RigidBody extends Component
{
    public Rectangle bounds;
    public RigidBodyInfo info;

    public RigidBody(int width, int height, RigidBodyInfo info)
    {
        this.bounds = new Rectangle(0, 0, width, height);
        this.info = info;
    }

    public RigidBody(int width, int height)
    {
        this(width, height, new RigidBodyInfo());
    }

    public boolean canCollide(RigidBody body)
    {
        if (body == null)
            return false;

        if (info.collisionCategory != Constants.Collisions.CATEGORY_NULL && info.collisionMask != Constants.Collisions.MASK_NULL)
        {
            if ((info.collisionMask & body.info.collisionCategory) == body.info.collisionCategory)
                if ((body.info.collisionMask & info.collisionCategory) == info.collisionCategory)
                    return true;
        }

        return false;
    }

    @Override
    public void onAdd()
    {
        bounds.x = owner.transform.position.x;
        bounds.y = owner.transform.position.y;
    }

    @Override
    public void onRemove()
    {
    }

    @Override
    public void update(float deltaTime)
    {
        bounds.x = owner.transform.position.x;
        bounds.y = owner.transform.position.y;
    }

    public static class RigidBodyInfo
    {
        public short collisionCategory = Constants.Collisions.CATEGORY_NULL;
        public short collisionMask = Constants.Collisions.MASK_NULL;
    }
}
