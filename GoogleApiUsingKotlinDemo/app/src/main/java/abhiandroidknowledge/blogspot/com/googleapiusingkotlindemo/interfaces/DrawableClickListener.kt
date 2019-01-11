package abhiandroidknowledge.blogspot.com.googleapiusingkotlindemo.interfaces

interface DrawableClickListener {

    enum class DrawablePosition {
        TOP, BOTTOM, LEFT, RIGHT
    };
    fun onClick(target: DrawablePosition)
}