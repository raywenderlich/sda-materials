/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.simplenote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.simplenote.R
import com.raywenderlich.simplenote.app.showToast
import com.raywenderlich.simplenote.model.InternalFileRepository
import com.raywenderlich.simplenote.model.NoteRepository
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private val repo: NoteRepository by lazy { InternalFileRepository(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btnWrite.setOnClickListener {
      // TODO Add code here
    }


    btnRead.setOnClickListener {
      if (edtFileName.text.isNotEmpty()) {
        try {
          val note = repo.getNote(edtFileName.text.toString())
          edtNoteText.setText(note.noteText)
        } catch (e: Exception) {
          showToast("File Read Failed")
        }
      } else {
        showToast("Please provide a Filename")
      }
    }

    btnDelete.setOnClickListener {
      if (edtFileName.text.isNotEmpty()) {
        try {
          if (repo.deleteNote(edtFileName.text.toString())) {
            showToast("File Deleted")
          } else {
            showToast("File Could Not Be Deleted")
          }
        } catch (e: Exception) {
          showToast("File Delete Failed")
        }
        edtFileName.text.clear()
        edtNoteText.text.clear()
      } else {
        showToast("Please provide a Filename")
      }
    }
  }
}
