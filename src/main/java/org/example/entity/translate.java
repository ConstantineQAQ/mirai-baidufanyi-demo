package org.example.entity;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import org.example.util.TransApi;

public class translate extends SimpleListenerHost {


    TransApi api = new TransApi("20220821001314661", "fae8vmmnU6vqrezAmVNB");


    @EventHandler
    public ListeningStatus translateIn(GroupMessageEvent event){
        translateReply(event);

        return ListeningStatus.LISTENING;
    }

    public void translateReply(GroupMessageEvent event){
        String trans_in = event.getMessage().contentToString();
        if(trans_in.matches("^(?:(?:翻译)|(?:翻译一下)|(?:兔兔翻译一下)|(?:解释)|(?:兔兔解释一下)|(?:解释一下)|(?:translate))[\\s]*$"))
        {
            event.getGroup().sendMessage("请输入正确的表达式，用法示例：" +
                    "\n" + "翻译 apple" +
                    "\n" + "翻译一下 apple"+
                    "\n" + "兔兔翻译一下 apple");
        }else if(trans_in.contains("翻译") || trans_in.contains("翻译一下") || trans_in.contains("兔兔翻译一下")){
            if(trans_in.length()>trans_in.indexOf(' ')) {
                String trans = trans_in.substring(trans_in.indexOf(' '));
                JSONObject jsonObject = JSONObject.parseObject(api.getTransResult(trans, "auto", "zh"));
                JSONArray trans_result = jsonObject.getJSONArray("trans_result");
                String result = trans_result.getJSONObject(0).get("dst").toString();
                if (jsonObject.getString("from").equals("zh")) {

                } else {
                    event.getGroup().sendMessage(
                            "上面这句话的中文翻译为：" + result +
                                    "\n" + jsonObject.getString("from")
                    );
                }
            }
        }
    }

    public void transNextReply(GroupMessageEvent event){

    }
}
