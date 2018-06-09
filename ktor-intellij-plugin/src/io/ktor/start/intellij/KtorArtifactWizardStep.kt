package io.ktor.start.intellij

import com.intellij.ide.util.projectWizard.*
import com.intellij.uiDesigner.core.*
import io.ktor.start.intellij.util.*
import javax.swing.*

class KtorArtifactWizardStep(val config: KtorModuleConfig) : ModuleWizardStep() {
    lateinit var groupId: JTextField
    lateinit var artifactId: JTextField
    lateinit var version: JTextField

    val panel = JPanel().apply {
        layout = GridLayoutManager(5, 2)
        fun addLabelText(name: String, value: String, row: Int): JTextField {
            val tfield = JTextField(value)
            addAtGrid(
                JLabel(name),
                row = row,
                column = 0,
                anchor = GridConstraints.ANCHOR_WEST,
                fill = GridConstraints.FILL_NONE,
                HSizePolicy = GridConstraints.SIZEPOLICY_FIXED,
                VSizePolicy = GridConstraints.SIZEPOLICY_FIXED
            )
            addAtGrid(
                tfield,
                row = row,
                column = 1,
                anchor = GridConstraints.ANCHOR_WEST,
                fill = GridConstraints.FILL_HORIZONTAL,
                HSizePolicy = GridConstraints.SIZEPOLICY_CAN_GROW or GridConstraints.SIZEPOLICY_WANT_GROW,
                VSizePolicy = GridConstraints.SIZEPOLICY_FIXED
            )
            return tfield
        }
        addAtGrid(
            JLabel(""),
            row = 0,
            column = 0,
            HSizePolicy = GridConstraints.SIZEPOLICY_FIXED,
            VSizePolicy = GridConstraints.SIZEPOLICY_FIXED
        )
        groupId = addLabelText("GroupId", config.artifactGroup, row = 1)
        artifactId = addLabelText("ArtifactId", config.artifactId, row = 2)
        version = addLabelText("Version", config.artifactVersion, row = 3)
        add(Spacer().apply {}, GridConstraints().apply {
            row = 4; column = 0; fill =
                GridConstraints.FILL_VERTICAL
        })
    }

    override fun updateDataModel() {
        config.artifactGroup = groupId.text
        config.artifactId = artifactId.text
        config.artifactVersion = version.text
    }

    override fun getComponent() = panel
}