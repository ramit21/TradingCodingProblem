package main.com.trade.service;

public interface ServiceIntf<I, O> {
	O execute(I input);
}
