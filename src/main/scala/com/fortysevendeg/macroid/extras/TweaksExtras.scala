package com.fortysevendeg.macroid.extras

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v7.widget.{CardView, RecyclerView, Toolbar}
import android.util.TypedValue
import android.view.ViewGroup.LayoutParams._
import android.view.ViewGroup.MarginLayoutParams
import android.view.{View, ViewGroup}
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget._
import macroid.FullDsl._
import macroid.{AppContext, Tweak}

object ViewTweaks {
  type W = View

  val vMatchParent: Tweak[View] = lp[ViewGroup](MATCH_PARENT, MATCH_PARENT)

  val vWrapContent: Tweak[View] = lp[ViewGroup](WRAP_CONTENT, WRAP_CONTENT)

  val vMatchWidth: Tweak[View] = lp[ViewGroup](MATCH_PARENT, WRAP_CONTENT)

  val vMatchHeight: Tweak[View] = lp[ViewGroup](WRAP_CONTENT, MATCH_PARENT)

  def vContentSizeMatchWidth(h: Int): Tweak[View] = lp[ViewGroup](MATCH_PARENT, h)

  def vContentSizeMatchHeight(w: Int): Tweak[View] = lp[ViewGroup](w, MATCH_PARENT)

  def vMargins(margin: Int): Tweak[W] = Tweak[W] {
    _.getLayoutParams match {
      case lp: MarginLayoutParams ⇒
        lp.setMargins(margin, margin, margin, margin)
      case _ ⇒
    }
  }

  def vMargin(
      marginLeft: Int = 0,
      marginTop: Int = 0,
      marginRight: Int = 0,
      marginBottom: Int = 0): Tweak[W] = Tweak[W] {
    view ⇒
      view
          .getLayoutParams
          .asInstanceOf[ViewGroup.MarginLayoutParams]
          .setMargins(marginLeft, marginTop, marginRight, marginBottom)
      view.requestLayout()
  }

  def vPaddings(padding: Int): Tweak[W] = Tweak[W](_.setPadding(padding, padding, padding, padding))

  def vPadding(
      paddingLeft: Int = 0,
      paddingTop: Int = 0,
      paddingRight: Int = 0,
      paddingBottom: Int = 0): Tweak[W] = Tweak[W](_.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom))

  def vActivated(activated: Boolean): Tweak[W] = Tweak[W](_.setActivated(activated))

  def vBackground(res: Int): Tweak[W] = Tweak[W](_.setBackgroundResource(res))

  def vBackgroundColor(color: Int): Tweak[W] = Tweak[W](_.setBackgroundColor(color))

  def vBackground(drawable: Drawable): Tweak[W] = Tweak[W](_.setBackground(drawable))

  def vTag(tag: String): Tweak[W] = Tweak[W](_.setTag(tag))

  def vTransformation(x: Int = 0, y: Int = 0): Tweak[W] = Tweak[W] {
    view ⇒
      view.setTranslationX(x)
      view.setTranslationY(y)
  }

  val vGone: Tweak[View] = Tweak[View](_.setVisibility(View.GONE))

  val vVisible: Tweak[View] = Tweak[View](_.setVisibility(View.VISIBLE))

  val vInvisible: Tweak[View] = Tweak[View](_.setVisibility(View.INVISIBLE))

}

object ImageViewTweaks {
  type W = ImageView

  def ivSrc(drawable: Drawable): Tweak[W] = Tweak[W](_.setImageDrawable(drawable))

  def ivSrc(res: Int): Tweak[W] = Tweak[W](_.setImageResource(res))
}

object LinearLayoutTweaks {
  type W = LinearLayout

  val llHorizontal: Tweak[W] = Tweak[W](_.setOrientation(LinearLayout.HORIZONTAL))

  val llVertical: Tweak[W] = Tweak[W](_.setOrientation(LinearLayout.VERTICAL))

  val llMatchWeightVertical: Tweak[View] = lp[W](MATCH_PARENT, 0, 1)

  val llMatchWeightHorizontal: Tweak[View] = lp[W](0, MATCH_PARENT, 1)

  val llWrapWeightVertical: Tweak[View] = lp[W](WRAP_CONTENT, 0, 1)

  val llWrapWeightHorizontal: Tweak[View] = lp[W](0, WRAP_CONTENT, 1)

  def llGravity(gravity: Int): Tweak[W] = Tweak[W](_.setGravity(gravity))

  def llDividerPadding(res: Int, padding: Int)(implicit appContext: AppContext): Tweak[W] = Tweak[W] {
    view ⇒
      view.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE)
      view.setDividerDrawable(appContext.get.getResources.getDrawable(res))
      view.setDividerPadding(padding)
  }

  def llLayoutGravity(gravity: Int): Tweak[View] = Tweak[View] {
    view ⇒
      val param = new LinearLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
      param.gravity = gravity
      view.setLayoutParams(param)
  }
}

object FrameLayoutTweaks {
  type W = FrameLayout

  def flContentSize(w: Int, h: Int) = lp[W](w, h)

  val flMatchWeightVertical: Tweak[View] = lp[W](MATCH_PARENT, 0, 1)

  val flMatchWeightHorizontal: Tweak[View] = lp[W](0, MATCH_PARENT, 1)

  def flLayoutGravity(gravity: Int): Tweak[View] = Tweak[View] { view ⇒
    val param = new FrameLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
    param.gravity = gravity
    view.setLayoutParams(param)
  }

}

object TableLayoutTweaks {
  type W = TableLayout

  def tlLayoutMargins(value: Int): Tweak[View] = Tweak[View] {
    view ⇒
      val param = new TableLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
      param.setMargins(value, value, value, value)
      view.setLayoutParams(param)
  }

  def tlStretchAllColumns(stretchAllColumns: Boolean): Tweak[W] = Tweak[W](_.setStretchAllColumns(stretchAllColumns))
}

object TableRowTweaks {
  type W = TableRow

  def trLayoutGravity(gravity: Int): Tweak[View] = Tweak[View] {
    view ⇒
      val param = new TableRow.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
      param.gravity = gravity
      view.setLayoutParams(param)
  }

  def trLayoutMargins(value: Int): Tweak[View] = Tweak[View] {
    view ⇒
      val param = new TableRow.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
      param.setMargins(value, value, value, value)
      view.setLayoutParams(param)
  }
}

object RecyclerViewTweaks {
  type W = RecyclerView

  val fixedSize: Tweak[W] = Tweak[W](_.setHasFixedSize(true))
}

object CardViewTweaks {
  type W = CardView

  def cvRadius(radius: Float): Tweak[W] = Tweak[W](_.setRadius(radius))
}

object TextTweaks {
  type W = TextView

  def tvColor(color: Int): Tweak[W] = Tweak[W](_.setTextColor(color))

  val tvBold: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.BOLD))

  val tvItalic: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.ITALIC))

  val tvBoldItalic: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(x.getTypeface, Typeface.BOLD_ITALIC))

  val tvNormalLight: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL)))

  val tvBoldLight: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD)))

  val tvItalicLight: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.ITALIC)))

  val tvBoldItalicLight: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-light", Typeface.BOLD_ITALIC)))

  val tvNormalCondensed: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL)))

  val tvBoldCondensed: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD)))

  val tvItalicCondensed: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.ITALIC)))

  val tvBoldItalicCondensed: Tweak[W] = Tweak[W](x ⇒ x.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD_ITALIC)))

  def tvSize(points: Int): Tweak[W] = Tweak[W](_.setTextSize(TypedValue.COMPLEX_UNIT_SP, points))

  def tvLines(lines: Int): Tweak[W] = Tweak[W](_.setLines(lines))

  def tvMaxLines(lines: Int): Tweak[W] = Tweak[W](_.setMaxLines(lines))

  def tvGravity(gravity: Int): Tweak[W] = Tweak[W](_.setGravity(gravity))

  def tvText(text: String): Tweak[W] = Tweak[W](_.setText(text))

  def tvText(text: Int): Tweak[W] = Tweak[W](_.setText(text))
}

object ToolbarTweaks {
  type W = Toolbar

  def tbTitle(title: String): Tweak[W] = Tweak[W](_.setTitle(title))

  def tbTitle(title: Int): Tweak[W] = Tweak[W](_.setTitle(title))

}

object SeekBarTweaks {
  type W = SeekBar

  def sbMax(maxValue: Int): Tweak[W] = Tweak[W](_.setMax(maxValue))

  def sbProgress(progressValue: Int): Tweak[W] = Tweak[W](_.setProgress(progressValue))

  def sbOnSeekBarChangeListener(listener: OnSeekBarChangeListener): Tweak[W] = Tweak[W](_.setOnSeekBarChangeListener(listener))
}