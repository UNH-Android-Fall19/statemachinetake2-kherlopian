package edu.newhaven.krikorherlopian.android.statemachine.activity


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import edu.newhaven.krikorherlopian.android.statemachine.R
import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich
import edu.newhaven.krikorherlopian.android.statemachine.model.SandwichType
import edu.newhaven.krikorherlopian.android.statemachine.actions.Actions
import edu.newhaven.krikorherlopian.android.statemachine.states.*
import kotlinx.android.synthetic.main.activity_sandwich.*
import kotlin.properties.Delegates

class SandwichActivity : AppCompatActivity() {

    private val predefinedSandwiches: MutableList<Sandwich> = mutableListOf()

    var currentState by Delegates.observable<SandwichState>(
        SandwichList(
            emptyList()
        ), { _, old, new ->
            renderViewState(new, old)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildPredefinedSandwiches()
        setContentView(R.layout.activity_sandwich)
        showSandwichList(emptyList())
    }

    private fun buildPredefinedSandwiches() {
        predefinedSandwiches.add(Sandwich("Meatball", SandwichType.GRINDER))
        predefinedSandwiches.add(Sandwich("Greek", SandwichType.WRAP))
        predefinedSandwiches.add(Sandwich("Italian", SandwichType.GRINDER))
        predefinedSandwiches.add(Sandwich("Caesar", SandwichType.WRAP))
        predefinedSandwiches.add(Sandwich("BLT", SandwichType.WRAP))
        predefinedSandwiches.add(Sandwich("Chicken", SandwichType.GRINDER))
    }

    private fun renderViewState(newState: SandwichState, oldState: SandwichState) {
        when (newState) {
            is SandwichList -> showSandwichList(newState.sandwiches)
            is AddSandwich -> showAddSandwichView(predefinedSandwiches)
            is NameSandwich -> showSandwichInputFields()
            is ModifySandwich -> showSandwichInputFields()
        }

        when (oldState) {
            is SandwichList -> hideSandwichList()
            is AddSandwich -> hideAddSandwichView()
            is NameSandwich -> hideSandwichInputFields()
            is ModifySandwich -> hideSandwichInputFields()
        }
    }

    private fun showSandwichList(sandwiches: List<Sandwich>) {
        sandwich_list_container.visibility = View.VISIBLE
        favorite_sandwiches_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sandwiches)
        add_sandwich_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.AddSandwichClicked())
        }
        add_sandwich_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.AddSandwichClicked())
        }
        favorite_sandwiches_listview.setOnItemClickListener { _, _, position, _ ->
            val selectedSandwich = sandwiches[position]
            sandwich_name.setText(selectedSandwich.name)
            currentState = currentState.consumeAction(Actions.ModifySandwichSelected(selectedSandwich))
        }
    }

    private fun hideSandwichList() {
        sandwich_list_container.visibility = View.GONE
    }

    private fun showAddSandwichView(predefinedSandwiches: List<Sandwich>) {
        add_sandwich_container.visibility = View.VISIBLE
        wrap_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.SandwichTypeSelected(SandwichType.WRAP))
        }
        grinder_button.setOnClickListener {
            currentState = currentState.consumeAction(Actions.SandwichTypeSelected(SandwichType.GRINDER))
        }
        predefined_sandwiches_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, predefinedSandwiches)
        predefined_sandwiches_listview.setOnItemClickListener { _, _, position, _ ->
            val selectedSandwich = predefinedSandwiches[position]
            currentState = currentState.consumeAction(Actions.PredefinedSandwichSelected(selectedSandwich))
        }
    }

    private fun hideAddSandwichView() {
        add_sandwich_container.visibility = View.GONE
    }

    private fun showSandwichInputFields() {
        sandwich_inputs_container.visibility = View.VISIBLE
        submit_button.setOnClickListener {
            val sandwichName = sandwich_name.text.toString()
            sandwich_name.text.clear()
            currentState = currentState.consumeAction(Actions.SubmitSandwichClicked(sandwichName))
        }
    }

    private fun hideSandwichInputFields() {
        sandwich_inputs_container.visibility = View.GONE
    }

}