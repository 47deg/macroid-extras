/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortysevendeg.macroid.extras

import android.graphics.drawable.{TransitionDrawable, Drawable}
import android.graphics.{Bitmap, Typeface}
import android.net.Uri
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.{CardView, RecyclerView, Toolbar}
import android.text.{Spanned, Spannable}
import android.util.TypedValue
import android.view.ViewGroup.LayoutParams._
import android.view.ViewGroup.MarginLayoutParams
import android.view.{View, ViewGroup}
import android.webkit.{WebViewClient, WebView}
import android.widget.ImageView.ScaleType
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

  def vMinHeight(height: Int): Tweak[W] = Tweak[W](_.setMinimumHeight(height))

  def vMinWidth(width: Int): Tweak[W] = Tweak[W](_.setMinimumWidth(width))

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

  def vBackgroundColorResource(color: Int)(implicit appContext: AppContext): Tweak[W] =
    Tweak[W](_.setBackgroundColor(appContext.get.getResources.getColor(color)))

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

  def vScrollBarStyle(style: Int): Tweak[W] = Tweak[W](_.setScrollBarStyle(style))
  
  def vBackgroundTransition(durationMilis: Int, reverse: Boolean = false): Tweak[W] = Tweak[W] {
    view ⇒
      val transitionBackground = view.getBackground.asInstanceOf[TransitionDrawable]
      if (reverse) transitionBackground.reverseTransition(durationMilis) else transitionBackground.startTransition(durationMilis)
  }

}

object ViewGroupTweaks {
  type W = ViewGroup

  def vgAddView[V <: View](view: V): Tweak[W] = Tweak[W](_.addView(view))

  def vgAddViews[V <: View](views: Seq[V]): Tweak[W] = Tweak[W] {
    rootView =>
      views map (rootView.addView(_))
  }

  val vgRemoveAllViews: Tweak[W] = Tweak[W](_.removeAllViews())

  def vgRemoveView(view: View): Tweak[W] = Tweak[W](_.removeView(view))

  def vgRemoveViewAt(index: Int): Tweak[W] = Tweak[W](_.removeViewAt(index))

  def vgClipToPadding(clip: Boolean): Tweak[W] = Tweak[W](_.setClipToPadding(clip))
}

object WebViewTweaks {
  type W = WebView

  def wvLoadUrl(url: String): Tweak[W] = Tweak[W](_.loadUrl(url))

  def wvClient(webViewClient: WebViewClient): Tweak[W] = Tweak[W](_.setWebViewClient(webViewClient))

}

object ImageViewTweaks {
  type W = ImageView

  def ivSrc(drawable: Drawable): Tweak[W] = Tweak[W](_.setImageDrawable(drawable))

  def ivSrc(res: Int): Tweak[W] = Tweak[W](_.setImageResource(res))

  def ivSrc(bitmap: Bitmap): Tweak[W] = Tweak[W](_.setImageBitmap(bitmap))

  def ivSrc(uri: Uri): Tweak[W] = Tweak[W](_.setImageURI(uri))

  def ivScaleType(scaleType: ScaleType): Tweak[W] = Tweak[W](_.setScaleType(scaleType))

  def ivCropToPadding(cropToPadding: Boolean): Tweak[W] = Tweak[W](_.setCropToPadding(cropToPadding))

  def ivBaseline(baseline: Int): Tweak[W] = Tweak[W](_.setBaseline(baseline))

  def ivBaselineAlignBottom(aligned: Boolean): Tweak[W] = Tweak[W](_.setBaselineAlignBottom(aligned))

  def ivImageAlpha(alpha: Int): Tweak[W] = Tweak[W](_.setImageAlpha(alpha))

  def ivAdjustViewBounds(adjustViewBounds: Boolean): Tweak[W] = Tweak[W](_.setAdjustViewBounds(adjustViewBounds))

}

object ScrollViewTweaks {
  type W = ScrollView

  val svRemoveVerticalScrollBar: Tweak[W] = Tweak[W](_.setVerticalScrollBarEnabled(false))

  val svRemoveHorizontalScrollBar: Tweak[W] = Tweak[W](_.setHorizontalScrollBarEnabled(false))

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

  def flForeground(drawable: Drawable): Tweak[W] = Tweak[W](_.setForeground(drawable))

  def flForegroundGravity(foregroundGravity: Int): Tweak[W] = Tweak[W](_.setForegroundGravity(foregroundGravity))
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

  val rvFixedSize: Tweak[W] = Tweak[W](_.setHasFixedSize(true))

  val rvNoFixedSize: Tweak[W] = Tweak[W](_.setHasFixedSize(false))

  def rvLayoutManager(layoutManager: RecyclerView.LayoutManager): Tweak[W] = Tweak[W](_.setLayoutManager(layoutManager))

  def rvAdapter[VH <: RecyclerView.ViewHolder](adapter: RecyclerView.Adapter[VH]): Tweak[W] = Tweak[W](_.setAdapter(adapter))

  def rvAddItemDecoration(decoration: RecyclerView.ItemDecoration): Tweak[W] = Tweak[W](_.addItemDecoration(decoration))

  def rvItemAnimator(animator: RecyclerView.ItemAnimator): Tweak[W] = Tweak[W](_.setItemAnimator(animator))

}

object CardViewTweaks {
  type W = CardView

  def cvRadius(radius: Float): Tweak[W] = Tweak[W](_.setRadius(radius))
}

object TextTweaks {
  type W = TextView

  def tvColor(color: Int): Tweak[W] = Tweak[W](_.setTextColor(color))

  def tvColorResource(resColor: Int)(implicit appContext: AppContext): Tweak[W] =
    Tweak[W](_.setTextColor(appContext.get.getResources.getColor(resColor)))

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

  def tvMinLines(lines: Int): Tweak[W] = Tweak[W](_.setMinLines(lines))

  val tvAllCaps: Tweak[W] = Tweak[W](_.setAllCaps(true))

  def tvGravity(gravity: Int): Tweak[W] = Tweak[W](_.setGravity(gravity))

  def tvText(text: String): Tweak[W] = Tweak[W](_.setText(text))

  def tvText(text: Int): Tweak[W] = Tweak[W](_.setText(text))

  def tvText(text: Spannable): Tweak[W] = Tweak[W](_.setText(text))

  def tvText(text: Spanned): Tweak[W] = Tweak[W](_.setText(text))

  def tvDrawablePadding(padding: Int): Tweak[W] = Tweak[W](_.setCompoundDrawablePadding(padding))

  def tvHint(text: String): Tweak[W] = Tweak[W](_.setHint(text))

  def tvHint(text: Int): Tweak[W] = Tweak[W](_.setHint(text))

  def tvCompoundDrawablesWithIntrinsicBounds(
      left: Int,
      top: Int,
      right: Int,
      bottom: Int): Tweak[W] = Tweak[W](_.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom))

}

object ToolbarTweaks {
  type W = Toolbar

  def tbTitle(title: String): Tweak[W] = Tweak[W](_.setTitle(title))

  def tbTitle(title: Int): Tweak[W] = Tweak[W](_.setTitle(title))

  def tbTextColor(color: Int): Tweak[W] = Tweak[W](_.setTitleTextColor(color))

}

object SeekBarTweaks {
  type W = SeekBar

  def sbMax(maxValue: Int): Tweak[W] = Tweak[W](_.setMax(maxValue))

  def sbProgress(progressValue: Int): Tweak[W] = Tweak[W](_.setProgress(progressValue))

  def sbOnSeekBarChangeListener(listener: OnSeekBarChangeListener): Tweak[W] = Tweak[W](_.setOnSeekBarChangeListener(listener))
}

object DrawerLayoutTweaks {
  type W = DrawerLayout

  def dlContentSize(w: Int, h: Int): Tweak[View] = lp[W](w, h)

  val dlMatchWeightVertical: Tweak[View] = lp[W](MATCH_PARENT, 0, 1)
  val dlMatchWeightHorizontal: Tweak[View] = lp[W](0, MATCH_PARENT, 1)

  def dlLayoutGravity(gravity: Int): Tweak[View] = Tweak[View] { view =>
    val param = new DrawerLayout.LayoutParams(view.getLayoutParams.width, view.getLayoutParams.height)
    param.gravity = gravity
    view.setLayoutParams(param)
  }

  def dlCloseDrawer(drawerMenuView: Option[View]): Tweak[W] = Tweak[W] { view =>
    drawerMenuView map view.closeDrawer
  }
}