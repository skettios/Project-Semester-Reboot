package com.skettios.lpreboot.util;

public final class Constants
{
    public static final class Collisions
    {
        public static final short CATEGORY_NULL = 0x0000;
        public static final short CATEGORY_PLAYER_BOUNDS = 0x0001;
        public static final short CATEGORY_PLAYER_HIT = 0x0002;
        public static final short CATEGORY_ENEMY_BOUNDS = 0x0004;
        public static final short CATEGORY_PLAYER_BULLET = 0x0008;
        public static final short CATEGORY_ENEMY_BULLET = 0x0010;
        public static final short CATEGORY_ITEM = 0x0020;
        public static final short CATEGORY_BOUNDS = 0x0040;
        public static final short CATEGORY_KILL_PLANE = 0x0080;

        public static final short MASK_NULL = CATEGORY_NULL;
        public static final short MASK_PLAYER_BOUNDS = CATEGORY_ENEMY_BOUNDS | CATEGORY_ITEM | CATEGORY_BOUNDS;
        public static final short MASK_PLAYER_HIT = CATEGORY_ENEMY_BOUNDS | CATEGORY_ENEMY_BULLET;
        public static final short MASK_ENEMY_BOUNDS = CATEGORY_PLAYER_BOUNDS | CATEGORY_PLAYER_BULLET | CATEGORY_PLAYER_HIT;
        public static final short MASK_PLAYER_BULLET = CATEGORY_ENEMY_BOUNDS | CATEGORY_ITEM | CATEGORY_KILL_PLANE;
        public static final short MASK_ENEMY_BULLET = CATEGORY_PLAYER_HIT | CATEGORY_KILL_PLANE;
        public static final short MASK_ITEM = CATEGORY_PLAYER_BOUNDS | CATEGORY_PLAYER_HIT | CATEGORY_PLAYER_BULLET | CATEGORY_KILL_PLANE;
        public static final short MASK_BOUNDS = CATEGORY_PLAYER_BOUNDS;
        public static final short MASK_KILL_PLANE = CATEGORY_ENEMY_BULLET | CATEGORY_PLAYER_HIT | CATEGORY_ITEM;
    }
}
