package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var page = 1
    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.toDoList)
        // RecyclerViewのレイアウトサイズを変更しない設定をONにする
        // パフォーマンス向上のための設定。
        recyclerView.setHasFixedSize(true)
        // RecyclerViewにlayoutManagerをセットする。
        // このlayoutManagerの種類によって「1列のリスト」なのか「２列のリスト」とかが選べる。
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // Adapter生成してRecyclerViewにセット
        mainAdapter = MainAdapter(createRowData(page))
        recyclerView.adapter = mainAdapter
    }

    /**
     * 20行追加する
     */
    private fun createRowData(page: Int): List<RowData> {
        val dataSet: MutableList<RowData> = ArrayList()
        var i = 1
        while (i < page * 20) {
            val data = RowData()
            data.hogeTitle = "hogeTitle" + Integer.toString(i)
            data.hogeContents = "hogeContents" + Integer.toString(i)
            val add = dataSet.add(data)
            i += 1
        }
        return dataSet
    }

    /**
     * 一行分のデータ
     */
    inner class RowData {
        var hogeTitle: String? = null
        var hogeContents: String? = null
    }



    //todoを追加する
    fun addToDoContent(view: View){
        val toDoContent = findViewById<EditText>(R.id.editTextToDo)
        val content = toDoContent.text.toString()

    }



}

class MainAdapter internal constructor(private var rowDataList: List<MainActivity.RowData>) : RecyclerView.Adapter<MainViewHolder>() {

    /**
     * ViewHolder作るメソッド
     * 最初しか呼ばれない。
     * ここでViewHolderのlayoutファイルをインフレーとして生成したViewHolderをRecyclerViewに返す。
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return MainViewHolder(view)
    }

    /**
     * ViewHolderとRecyclerViewをバインドする
     * 一行のViewに対して共通でやりたい処理をここで書く。今回はテキストのセットしかしてないけど。
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val rowData = rowDataList[position]
        holder.hogeTitle.text = rowData.hogeTitle
        holder.hogeContents.text = rowData.hogeContents
    }

    /**
     * リストの行数
     * @return
     */
    override fun getItemCount(): Int {
        return rowDataList.size
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var hogeTitle: TextView = itemView.findViewById(R.id.hoge_title_text_view)
    var hogeContents: TextView = itemView.findViewById(R.id.hoge_contents_text_view)
}