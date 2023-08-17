package id.haaweejee.rawgandroid.ui.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun String.extractTextFromHtml(): String {
    val doc: Document = Jsoup.parse(this)
    return doc.text()
}
