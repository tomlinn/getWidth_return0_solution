### There are explanation bellow and in code, you can just download to try.
<br>

# getWidth_return0_solution"
A simple example to demo how to fix "getWidth() return 0" in Fragment
<br>

# Explaination
#### Many people have seen lots of solution in stackoverflow or somewhere, it's usually using the "getViewTreeObserver".
#### But it always don't have some detail explainaton or completely code, which make people hard to understand how it works.
#### Thus, I post this example to let everyone understand principle of this.
<br>

# GIF demo
![image](https://i.imgur.com/N9z8XbV.gif)
<br>

# Code
```java
package com.example.getwidth_return0_solution;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Food_Fragment_Main extends Fragment {
    private ImageView mImageView;

    public Food_Fragment_Main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_food__fragment__main, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image_view);
        final TextView process_text = view.findViewById(R.id.process);
        final TextView info2 = view.findViewById(R.id.info2);

        // We just use the index 1 " txt[0] ", which make the value can be used in listener
        final String[] txt = {""};

        // When the page processing it can't get any value from "getWidth()" or "getHeight"
        final int non_working_Width = mImageView.getWidth();
        final int non_working_Height = mImageView.getHeight();
        txt[0] +="After set Value \"non_working_Width\" ="+non_working_Width+"\n";
        txt[0] +="After set Value \"non_working_Height\" ="+non_working_Height+"\n";

        // Adding an listener make it can be detected when the view finished.
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (view.getViewTreeObserver().isAlive()) {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);

                    int working_Width = mImageView.getWidth();
                    int working_Height = mImageView.getHeight();
                    txt[0] += "After set Value \"working_Height\" = " + working_Width + "\n";
                    txt[0] += "After set Value \"working_Height\" = " + working_Height + "\n";
                    process_text.setText(txt[0]);

                    // do somthing you want - start
                    do_something();
                    // do somthing you want - end
                }
                return true;

            }
        });
        process_text.setText(txt[0]);
        info2.setText("Explan:\nBoth  \"non_working_Width\" and  \"working_Width\"  are using [ mImageView.getWidth() ]\n\n It only get value from using \"getWidth\" in Listener\n\nAlso, you can see that the \"working_Width\" is be set after seting the \"non_working_Width\"\n\n Go go Food_Fragment_Main.java to check it out !\n(Using break point will more easily understand)");
        return view;
    }
    public void do_something (){
        Toast.makeText(getContext(), "Doing somthing Now", Toast.LENGTH_SHORT).show();
    }
}

```
