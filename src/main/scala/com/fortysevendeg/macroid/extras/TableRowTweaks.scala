/*
 *
 *   Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may
 *   not use this file except in compliance with the License. You may obtain
 *   a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.fortysevendeg.macroid.extras

import android.view.View
import android.view.ViewGroup.LayoutParams._
import android.widget.TableRow
import macroid.Tweak

object TableRowTweaks {
  type W = TableRow

  def trLayoutGravity(gravity: Int): Tweak[View] = Tweak[View] { view ⇒
    val param = new TableRow.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    param.gravity = gravity
    view.setLayoutParams(param)
  }

  def trLayoutMargins(value: Int): Tweak[View] = Tweak[View] { view ⇒
    val param = new TableRow.LayoutParams(view.getLayoutParams)
    param.setMargins(value, value, value, value)
    view.setLayoutParams(param)
  }
}
