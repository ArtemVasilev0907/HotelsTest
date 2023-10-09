package com.skydivers.hotelstest.booking.model


class Vec2(
    private var x: Float = .0f,
    private var y: Float = .0f
) {


    operator fun minus(vec: Vec2): Vec2 {

        return Vec2(x - vec.x, y - vec.y)
    }

    operator fun plus(vec: Vec2): Vec2 {

        return Vec2(x + vec.x, y + vec.y)
    }

    operator fun times(vec: Vec2): Vec2 {

        return Vec2(x * vec.x, y * vec.y)
    }

    operator fun div(vec: Vec2): Vec2 {

        return Vec2(x / vec.x, y / vec.y)
    }
}

class Vec3(
    private var x: Float = 0.0f,
    private var y: Float = 0.0f,
    private var z: Float = 0.0f
) {


    operator fun minus(vec: Vec3): Vec3 {

        return Vec3(x - vec.x, y - vec.y, z - vec.z)
    }

    operator fun plus(vec: Vec3): Vec3 {

        return Vec3(x + vec.x, y + vec.y, z + vec.z)
    }

    operator fun times(vec: Vec3): Vec3 {

        return Vec3(x * vec.x, y * vec.y, z * vec.z)
    }

    operator fun div(vec: Vec3): Vec3 {

        return Vec3(x / vec.x, y / vec.y, z / vec.z)


    }

}

