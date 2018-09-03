package com.xquant.platform.component.darren.web.spi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.xquant.platform.component.entity.instrument.Instrument;
import com.xquant.platform.component.instrument.api.service.notify.InstrumentActionIntercept;
import com.xquant.platform.component.instrument.enums.InstrumentActionEnum;
import com.xquant.platform.component.trade.api.dto.notify.InterceptResponse;

@Component
public class MyInstrumentActionIntercept implements InstrumentActionIntercept {

	@Override
	public InstrumentActionEnum[] supportInstrumentAction() {

		System.out.println("=======MyInstrumentActionIntercept=========supportInstrumentAction=============="
				+ new SimpleDateFormat("yyyyMMdd HH:mm:ss.sss").format(new Date()));
		return new InstrumentActionEnum[] { InstrumentActionEnum.INSERT_INSTRUMENT,
				InstrumentActionEnum.UPDATE_INSTRUMENT };
	}

	@Override
	public InterceptResponse notifyTradeAction(InstrumentActionEnum action, Instrument instrument) {
		System.out.println("=======MyInstrumentActionIntercept=========notifyTradeAction=============="
				+ new SimpleDateFormat("yyyyMMdd HH:mm:ss.sss").format(new Date()));
		return null;
	}

}
