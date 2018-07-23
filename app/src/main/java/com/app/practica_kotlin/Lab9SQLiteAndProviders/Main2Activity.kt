package com.app.practica_kotlin.Lab9SQLiteAndProviders

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.app.practica_kotlin.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.app_bar_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var myDBAdapter: MyDBAdapter? = null
    private val mAllFaculties = arrayOf("Engineering", "Business", "Arts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initializeViews()
        initializeDatabase()
        loadList()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initializeViews(){
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        faculties_spinner.adapter = ArrayAdapter(this@Main2Activity, android.R.layout.simple_list_item_1, mAllFaculties)

        add_student.setOnClickListener{
            myDBAdapter?.insertStudents(student_name.text.toString(), faculties_spinner.selectedItemPosition + 1)
            loadList()
        }
        delete_engineers.setOnClickListener { myDBAdapter?.deleteAllEngineers()
        loadList()}
    }

    private fun initializeDatabase(){
        myDBAdapter = MyDBAdapter(this@Main2Activity)
        myDBAdapter?.open()
    }

    private fun loadList(){
        val allStudents: List<String>? = myDBAdapter?.selectAllStudents()
        val adapter = ArrayAdapter(this@Main2Activity, android.R.layout.simple_list_item_1, allStudents)
        student_list.adapter =adapter
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
