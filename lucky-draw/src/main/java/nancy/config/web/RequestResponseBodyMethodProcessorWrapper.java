package nancy.config.web;

import nancy.base.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestResponseBodyMethodProcessorWrapper implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public RequestResponseBodyMethodProcessorWrapper(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    //决定了哪类类型的返回值将将使用该返回值处理器
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    //则是主要处理返回值的处理逻辑，并且将处理好的值返回给model，还可以处理该返回什么视图
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //returnValue是Controller请求方法执行完，返回值
        if(!(returnValue instanceof ResponseResult)){ //返回值本身就是需要的类型，不进行处理
            returnValue = ResponseResult.ok(returnValue);
        }
        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}