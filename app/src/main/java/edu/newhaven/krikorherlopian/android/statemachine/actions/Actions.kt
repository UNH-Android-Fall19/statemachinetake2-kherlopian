package edu.newhaven.krikorherlopian.android.statemachine.actions

import edu.newhaven.krikorherlopian.android.statemachine.model.Sandwich
import edu.newhaven.krikorherlopian.android.statemachine.model.SandwichType

sealed class Actions {
    class AddSandwichClicked: Actions()
    class SandwichTypeSelected(val type: SandwichType): Actions()
    class PredefinedSandwichSelected(val sandwich: Sandwich): Actions()
    class ModifySandwichSelected(val sandwich: Sandwich): Actions()
    class SubmitSandwichClicked(val sandwichName: String): Actions()
}