package app.yonezawa.yone.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val readRequestCode: Int = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //intentButtonクリック時にSecondActivityへ画面遷移
        intentButton.setOnClickListener {
            //Intent（this,遷移先のActivity名：：class.jave)⇒今いるActivityからSecondActivityに移動したい。this(今いるに場所）
            val toSecondActivityIntent = Intent(this, SecondActivity::class.java)
            startActivity(toSecondActivityIntent)

        }

        //playストアボタンを繰る￥リックしたときにplayストアを開く
        playStoreButton.setOnClickListener {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("https://play.google.com/store/apps")
            //遷移先のアプリ　Intent型変数。setPackege(アプリケーションID）
            playStoreIntent.setPackage("com.android.vending")
            startActivity(playStoreIntent)

        }

        //マップボタンクリック時に地図を開く
        mapButton.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW)
            //位置情報おデータを送っている
            mapIntent.data = Uri.parse("geo:35,6473,139.7360")
            //暗黙的インテントをつかう時は呼び出せるアプリがあるか確認する
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }

            //brownButtonクリック時にブラウザを開く
            browserButton.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW)
                browserIntent.data = Uri.parse("https://life-is-tech.com/")


            }
        }
        //ギャラリーボタンクリック時にギャラリーを開く
        galleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_TYPED_OPENABLE)
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, readRequestCode)
        }
    }

    //遷移先のアクティビティから結果を受け取る
            override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
                super.onActivityResult(requestCode, resultCode, resultData)

                if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK) {
                    resultData?.data?.also { uri ->
                        imageView.setImageURI(uri)

                    }
                }


            }
        }
