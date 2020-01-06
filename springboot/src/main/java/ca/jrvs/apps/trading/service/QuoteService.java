package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if tikcer is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker){
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid."));
  }

  /**
   * Update quote table against IEX source
   *  - get all quotes from the db
   *  - foreach ticker get IexQuote
   *  - convert iexQuote to quote entity
   *  - persist quote to db
   *
   * @throws ResourceNotFoundException if ticker is not found from IEX
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException for invalid input
   */
  public void updateMarketData(){

  }

  /**
   * Helper method: Map a IexQuote to a Quote entity
   * Note: `iexQuote.getLastestPrice() == null` if the stock market is closed.
   * Make sure set a default value for number fields.
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){

  }
}
