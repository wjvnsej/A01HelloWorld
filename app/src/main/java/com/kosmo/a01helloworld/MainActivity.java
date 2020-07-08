package com.kosmo.a01helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    Java에서의 출발점은 main()메소드였듯이 안드로이드의 출발점은
    onCreate()메소드이다. 수명주기(LifeCycle) 메소드들 중에서 첫번째로
    실행된다.
    */

    //전화번호 입력상자를 전역적으로 사용하기 위한 변수
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        xml로 생성된 레이아웃을 액티비티에 전개(Inflate)하는 역할을
        하는 메소드로, 해당 메소드가 실행되지 않으면 아무것도 보이지 않게된다.
        */
        setContentView(R.layout.activity_main);

        /*
        findViewById(res에 설정된 id)
            : XML에 설정된 id값을 통해 벝느 객체를 Java클래스로 가져와서
            click 리스너를 부착한다.

        리스너 부착방법1 : 버튼 객체에 직접 리스너 인터페이스를 사용한다.
         */
        Button btnNate = (Button)findViewById(R.id.btnNate);
        //리스너를 부착할 때 View.OnClickListener인터페이스를 익명클래스 형태로 사용한다.
        btnNate.setOnClickListener(new View.OnClickListener(){
            //익명클래스에서 미리 선언된 메소드를 오버라이딩 한다.
            @Override
            public void onClick(View v) {
                /*
                버튼을 눌렀을 때 토스트 메세지를 띄워준다. 토스트는 JS의
                alert()와 비슷하게 메시지를 일정시간 보여주는 역할을 한다.
                형식)
                    Toast.makeText(메세지를 띄울 컨텍스트(뷰),
                                    "메세지내용",
                                    "시간(Long, short)).show();
                 */
                Toast.makeText(v.getContext(),
                     "잠시후 네이트 페이지로 이동합니다.",
                     Toast.LENGTH_LONG).show();
                /*
                인텐트를 통해 새로운 액티비티를 띄워준다. 웹URL이
                전달되므로 외장 웹브라우저를 통해 네이트에 접속하게 된다.
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.nate.com"));
                startActivity(intent);
            }
        });////btnNate 버튼

        //버튼 객체 가져오기
        Button btnCall = (Button)findViewById(R.id.btnCall);
        Button btnNext = (Button)findViewById(R.id.btnNext);

        //전화번호 입력상자 가져오기
        editText = (EditText)findViewById(R.id.editText);

        //버튼객체에 리스너 부착방법2 : 외부에 생성된 리스너 객체를 이용한다.
        btnCall.setOnClickListener(listener);
        btnNext.setOnClickListener(listener);

    }////onCreate() End

    //onCreate() 메소드 외부에 리스너 객체 선언
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //버튼을 누를 때 전달되는 View객체를 통해 눌러진 버튼을 알아낼 수 있다.
            if (v.getId() == R.id.btnCall){
                /*
                전화걸기 버튼을 눌렀을 때
                입력상자에 입력된 내용을 가져와서 문자열로 변환
                */
                String callNumber = editText.getText().toString();
                //전화걸기 앱이 실행됨.
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:" + callNumber));
                startActivity(intent);
            }
            else {
                //화면전환 버튼을 눌렀을 때
                Intent intent = new Intent(v.getContext(),
                        SubActivity.class);
                //SubActivity 를 띄워준다.
                startActivity(intent);
            }
        }
    };////View.OnClickListener() End

    /*
    리스너 부착방법4 : 버튼의 onClick 속성을 이용한다. 이때 호출 할
        메소드명을 기술하면 아래 함수를 즉시 호출할 수 있다.
     */
    public void myBtnClick(View view){
        Toast.makeText(view.getContext(),
                "onClick 속성을 통한 버튼입니다. 저는 짧아요.",
                Toast.LENGTH_SHORT).show();
    }////myBtnClick() End

}////MainActivity End




















