/*
 * tiancheng copyrights reserved
 */

package interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.LoginDao;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static login.Login.EXPIRE;


public class SessionInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入 preHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie ck : cookies) {
            if (ck.getName().equals("token")) {
                token = ck.getValue();
                if (!token.isEmpty()) {
                    System.out.println("token is not empty");
                    Map userinfo = LoginDao.getUserInfo(token);
                    long last = (long) userinfo.get("last_request_time");
                    long now = new Date().getTime();
                    System.out.println("last: " + last);
                    System.out.println(" now: " + now);
                    if (now - last > EXPIRE) {
                        PrintWriter writer = response.getWriter();
                        JSONObject map = new JSONObject();
                        map.put("code", "fail");
                        map.put("message", "token expired");
                        writer.write(map.toString());
                        writer.flush();
                        writer.close();
                        return false;
                    } else {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
//
//        JSONObject ret = new JSONObject();
//        ret.put("code", "fail");
//        ret.put("message", "token expired");
//
//        PrintWriter writer = response.getWriter();
//        writer.write(ret.toString());
//        writer.flush();
//        writer.close();
//        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("进入 postHandle 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("进入 afterCompletion 方法..." + request.getRequestURL().toString() + "," + request.getRequestURI());
    }
}

