import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.ListItemBinding
import com.example.taskapp.helper.Todo


class MyAdapter(private var todoList: ArrayList<Todo>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var listener: OnClickListener? = null

    fun setListener(clickListener: OnClickListener) {
        this.listener = clickListener
    }

    fun updateData(newTodoList: List<com.example.taskapp.helper.Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: Todo = todoList[position]
        holder.bindItems(todo)

        holder.binding.root.setOnClickListener {
            listener?.onItemClick(todo, position)
        }

        holder.binding.btnDelete.setOnClickListener {
            listener?.onItemDelete(todo)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(todo: Todo) {
            binding.tvTitle.text = todo.title
            binding.tvDesc.text = todo.description
            binding.tvTimestamp.text = todo.timestamp
        }
    }

    interface OnClickListener {
        fun onItemClick(todo: Todo, position: Int)
        fun onItemDelete(todo: Todo)
    }
}
