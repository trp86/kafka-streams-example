/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package types;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class CreditCardTxn extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8353112073080500958L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CreditCardTxn\",\"namespace\":\"types\",\"fields\":[{\"name\":\"customer_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"customer_type\",\"type\":[\"null\",\"string\"]},{\"name\":\"customer_address\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Address\",\"fields\":[{\"name\":\"AddressLine\",\"type\":[\"null\",\"string\"]},{\"name\":\"City\",\"type\":[\"null\",\"string\"]},{\"name\":\"State\",\"type\":[\"null\",\"string\"]},{\"name\":\"PinCode\",\"type\":[\"null\",\"string\"]},{\"name\":\"ContactNumber\",\"type\":[\"null\",\"string\"]}]}]},{\"name\":\"is_logged_in\",\"type\":[\"null\",\"boolean\"]},{\"name\":\"cookie_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"card_no\",\"type\":[\"null\",\"long\"]},{\"name\":\"txn_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"txn_amount\",\"type\":[\"null\",\"double\"]},{\"name\":\"merchant_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"merchant_name\",\"type\":[\"null\",\"string\"]},{\"name\":\"merchant_address\",\"type\":[\"null\",\"Address\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence customer_id;
  @Deprecated public java.lang.CharSequence customer_type;
  @Deprecated public types.Address customer_address;
  @Deprecated public java.lang.Boolean is_logged_in;
  @Deprecated public java.lang.CharSequence cookie_id;
  @Deprecated public java.lang.Long card_no;
  @Deprecated public java.lang.CharSequence txn_id;
  @Deprecated public java.lang.Double txn_amount;
  @Deprecated public java.lang.CharSequence merchant_id;
  @Deprecated public java.lang.CharSequence merchant_name;
  @Deprecated public types.Address merchant_address;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CreditCardTxn() {}

  /**
   * All-args constructor.
   * @param customer_id The new value for customer_id
   * @param customer_type The new value for customer_type
   * @param customer_address The new value for customer_address
   * @param is_logged_in The new value for is_logged_in
   * @param cookie_id The new value for cookie_id
   * @param card_no The new value for card_no
   * @param txn_id The new value for txn_id
   * @param txn_amount The new value for txn_amount
   * @param merchant_id The new value for merchant_id
   * @param merchant_name The new value for merchant_name
   * @param merchant_address The new value for merchant_address
   */
  public CreditCardTxn(java.lang.CharSequence customer_id, java.lang.CharSequence customer_type, types.Address customer_address, java.lang.Boolean is_logged_in, java.lang.CharSequence cookie_id, java.lang.Long card_no, java.lang.CharSequence txn_id, java.lang.Double txn_amount, java.lang.CharSequence merchant_id, java.lang.CharSequence merchant_name, types.Address merchant_address) {
    this.customer_id = customer_id;
    this.customer_type = customer_type;
    this.customer_address = customer_address;
    this.is_logged_in = is_logged_in;
    this.cookie_id = cookie_id;
    this.card_no = card_no;
    this.txn_id = txn_id;
    this.txn_amount = txn_amount;
    this.merchant_id = merchant_id;
    this.merchant_name = merchant_name;
    this.merchant_address = merchant_address;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return customer_id;
    case 1: return customer_type;
    case 2: return customer_address;
    case 3: return is_logged_in;
    case 4: return cookie_id;
    case 5: return card_no;
    case 6: return txn_id;
    case 7: return txn_amount;
    case 8: return merchant_id;
    case 9: return merchant_name;
    case 10: return merchant_address;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: customer_id = (java.lang.CharSequence)value$; break;
    case 1: customer_type = (java.lang.CharSequence)value$; break;
    case 2: customer_address = (types.Address)value$; break;
    case 3: is_logged_in = (java.lang.Boolean)value$; break;
    case 4: cookie_id = (java.lang.CharSequence)value$; break;
    case 5: card_no = (java.lang.Long)value$; break;
    case 6: txn_id = (java.lang.CharSequence)value$; break;
    case 7: txn_amount = (java.lang.Double)value$; break;
    case 8: merchant_id = (java.lang.CharSequence)value$; break;
    case 9: merchant_name = (java.lang.CharSequence)value$; break;
    case 10: merchant_address = (types.Address)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'customer_id' field.
   * @return The value of the 'customer_id' field.
   */
  public java.lang.CharSequence getCustomerId() {
    return customer_id;
  }

  /**
   * Sets the value of the 'customer_id' field.
   * @param value the value to set.
   */
  public void setCustomerId(java.lang.CharSequence value) {
    this.customer_id = value;
  }

  /**
   * Gets the value of the 'customer_type' field.
   * @return The value of the 'customer_type' field.
   */
  public java.lang.CharSequence getCustomerType() {
    return customer_type;
  }

  /**
   * Sets the value of the 'customer_type' field.
   * @param value the value to set.
   */
  public void setCustomerType(java.lang.CharSequence value) {
    this.customer_type = value;
  }

  /**
   * Gets the value of the 'customer_address' field.
   * @return The value of the 'customer_address' field.
   */
  public types.Address getCustomerAddress() {
    return customer_address;
  }

  /**
   * Sets the value of the 'customer_address' field.
   * @param value the value to set.
   */
  public void setCustomerAddress(types.Address value) {
    this.customer_address = value;
  }

  /**
   * Gets the value of the 'is_logged_in' field.
   * @return The value of the 'is_logged_in' field.
   */
  public java.lang.Boolean getIsLoggedIn() {
    return is_logged_in;
  }

  /**
   * Sets the value of the 'is_logged_in' field.
   * @param value the value to set.
   */
  public void setIsLoggedIn(java.lang.Boolean value) {
    this.is_logged_in = value;
  }

  /**
   * Gets the value of the 'cookie_id' field.
   * @return The value of the 'cookie_id' field.
   */
  public java.lang.CharSequence getCookieId() {
    return cookie_id;
  }

  /**
   * Sets the value of the 'cookie_id' field.
   * @param value the value to set.
   */
  public void setCookieId(java.lang.CharSequence value) {
    this.cookie_id = value;
  }

  /**
   * Gets the value of the 'card_no' field.
   * @return The value of the 'card_no' field.
   */
  public java.lang.Long getCardNo() {
    return card_no;
  }

  /**
   * Sets the value of the 'card_no' field.
   * @param value the value to set.
   */
  public void setCardNo(java.lang.Long value) {
    this.card_no = value;
  }

  /**
   * Gets the value of the 'txn_id' field.
   * @return The value of the 'txn_id' field.
   */
  public java.lang.CharSequence getTxnId() {
    return txn_id;
  }

  /**
   * Sets the value of the 'txn_id' field.
   * @param value the value to set.
   */
  public void setTxnId(java.lang.CharSequence value) {
    this.txn_id = value;
  }

  /**
   * Gets the value of the 'txn_amount' field.
   * @return The value of the 'txn_amount' field.
   */
  public java.lang.Double getTxnAmount() {
    return txn_amount;
  }

  /**
   * Sets the value of the 'txn_amount' field.
   * @param value the value to set.
   */
  public void setTxnAmount(java.lang.Double value) {
    this.txn_amount = value;
  }

  /**
   * Gets the value of the 'merchant_id' field.
   * @return The value of the 'merchant_id' field.
   */
  public java.lang.CharSequence getMerchantId() {
    return merchant_id;
  }

  /**
   * Sets the value of the 'merchant_id' field.
   * @param value the value to set.
   */
  public void setMerchantId(java.lang.CharSequence value) {
    this.merchant_id = value;
  }

  /**
   * Gets the value of the 'merchant_name' field.
   * @return The value of the 'merchant_name' field.
   */
  public java.lang.CharSequence getMerchantName() {
    return merchant_name;
  }

  /**
   * Sets the value of the 'merchant_name' field.
   * @param value the value to set.
   */
  public void setMerchantName(java.lang.CharSequence value) {
    this.merchant_name = value;
  }

  /**
   * Gets the value of the 'merchant_address' field.
   * @return The value of the 'merchant_address' field.
   */
  public types.Address getMerchantAddress() {
    return merchant_address;
  }

  /**
   * Sets the value of the 'merchant_address' field.
   * @param value the value to set.
   */
  public void setMerchantAddress(types.Address value) {
    this.merchant_address = value;
  }

  /**
   * Creates a new CreditCardTxn RecordBuilder.
   * @return A new CreditCardTxn RecordBuilder
   */
  public static types.CreditCardTxn.Builder newBuilder() {
    return new types.CreditCardTxn.Builder();
  }

  /**
   * Creates a new CreditCardTxn RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CreditCardTxn RecordBuilder
   */
  public static types.CreditCardTxn.Builder newBuilder(types.CreditCardTxn.Builder other) {
    return new types.CreditCardTxn.Builder(other);
  }

  /**
   * Creates a new CreditCardTxn RecordBuilder by copying an existing CreditCardTxn instance.
   * @param other The existing instance to copy.
   * @return A new CreditCardTxn RecordBuilder
   */
  public static types.CreditCardTxn.Builder newBuilder(types.CreditCardTxn other) {
    return new types.CreditCardTxn.Builder(other);
  }

  /**
   * RecordBuilder for CreditCardTxn instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CreditCardTxn>
    implements org.apache.avro.data.RecordBuilder<CreditCardTxn> {

    private java.lang.CharSequence customer_id;
    private java.lang.CharSequence customer_type;
    private types.Address customer_address;
    private types.Address.Builder customer_addressBuilder;
    private java.lang.Boolean is_logged_in;
    private java.lang.CharSequence cookie_id;
    private java.lang.Long card_no;
    private java.lang.CharSequence txn_id;
    private java.lang.Double txn_amount;
    private java.lang.CharSequence merchant_id;
    private java.lang.CharSequence merchant_name;
    private types.Address merchant_address;
    private types.Address.Builder merchant_addressBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(types.CreditCardTxn.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[0].schema(), other.customer_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.customer_type)) {
        this.customer_type = data().deepCopy(fields()[1].schema(), other.customer_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.customer_address)) {
        this.customer_address = data().deepCopy(fields()[2].schema(), other.customer_address);
        fieldSetFlags()[2] = true;
      }
      if (other.hasCustomerAddressBuilder()) {
        this.customer_addressBuilder = types.Address.newBuilder(other.getCustomerAddressBuilder());
      }
      if (isValidValue(fields()[3], other.is_logged_in)) {
        this.is_logged_in = data().deepCopy(fields()[3].schema(), other.is_logged_in);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.cookie_id)) {
        this.cookie_id = data().deepCopy(fields()[4].schema(), other.cookie_id);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.card_no)) {
        this.card_no = data().deepCopy(fields()[5].schema(), other.card_no);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.txn_id)) {
        this.txn_id = data().deepCopy(fields()[6].schema(), other.txn_id);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.txn_amount)) {
        this.txn_amount = data().deepCopy(fields()[7].schema(), other.txn_amount);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.merchant_id)) {
        this.merchant_id = data().deepCopy(fields()[8].schema(), other.merchant_id);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.merchant_name)) {
        this.merchant_name = data().deepCopy(fields()[9].schema(), other.merchant_name);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.merchant_address)) {
        this.merchant_address = data().deepCopy(fields()[10].schema(), other.merchant_address);
        fieldSetFlags()[10] = true;
      }
      if (other.hasMerchantAddressBuilder()) {
        this.merchant_addressBuilder = types.Address.newBuilder(other.getMerchantAddressBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing CreditCardTxn instance
     * @param other The existing instance to copy.
     */
    private Builder(types.CreditCardTxn other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.customer_id)) {
        this.customer_id = data().deepCopy(fields()[0].schema(), other.customer_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.customer_type)) {
        this.customer_type = data().deepCopy(fields()[1].schema(), other.customer_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.customer_address)) {
        this.customer_address = data().deepCopy(fields()[2].schema(), other.customer_address);
        fieldSetFlags()[2] = true;
      }
      this.customer_addressBuilder = null;
      if (isValidValue(fields()[3], other.is_logged_in)) {
        this.is_logged_in = data().deepCopy(fields()[3].schema(), other.is_logged_in);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.cookie_id)) {
        this.cookie_id = data().deepCopy(fields()[4].schema(), other.cookie_id);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.card_no)) {
        this.card_no = data().deepCopy(fields()[5].schema(), other.card_no);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.txn_id)) {
        this.txn_id = data().deepCopy(fields()[6].schema(), other.txn_id);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.txn_amount)) {
        this.txn_amount = data().deepCopy(fields()[7].schema(), other.txn_amount);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.merchant_id)) {
        this.merchant_id = data().deepCopy(fields()[8].schema(), other.merchant_id);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.merchant_name)) {
        this.merchant_name = data().deepCopy(fields()[9].schema(), other.merchant_name);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.merchant_address)) {
        this.merchant_address = data().deepCopy(fields()[10].schema(), other.merchant_address);
        fieldSetFlags()[10] = true;
      }
      this.merchant_addressBuilder = null;
    }

    /**
      * Gets the value of the 'customer_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getCustomerId() {
      return customer_id;
    }

    /**
      * Sets the value of the 'customer_id' field.
      * @param value The value of 'customer_id'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setCustomerId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.customer_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'customer_id' field has been set.
      * @return True if the 'customer_id' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'customer_id' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearCustomerId() {
      customer_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer_type' field.
      * @return The value.
      */
    public java.lang.CharSequence getCustomerType() {
      return customer_type;
    }

    /**
      * Sets the value of the 'customer_type' field.
      * @param value The value of 'customer_type'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setCustomerType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.customer_type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'customer_type' field has been set.
      * @return True if the 'customer_type' field has been set, false otherwise.
      */
    public boolean hasCustomerType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'customer_type' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearCustomerType() {
      customer_type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer_address' field.
      * @return The value.
      */
    public types.Address getCustomerAddress() {
      return customer_address;
    }

    /**
      * Sets the value of the 'customer_address' field.
      * @param value The value of 'customer_address'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setCustomerAddress(types.Address value) {
      validate(fields()[2], value);
      this.customer_addressBuilder = null;
      this.customer_address = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'customer_address' field has been set.
      * @return True if the 'customer_address' field has been set, false otherwise.
      */
    public boolean hasCustomerAddress() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'customer_address' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public types.Address.Builder getCustomerAddressBuilder() {
      if (customer_addressBuilder == null) {
        if (hasCustomerAddress()) {
          setCustomerAddressBuilder(types.Address.newBuilder(customer_address));
        } else {
          setCustomerAddressBuilder(types.Address.newBuilder());
        }
      }
      return customer_addressBuilder;
    }

    /**
     * Sets the Builder instance for the 'customer_address' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public types.CreditCardTxn.Builder setCustomerAddressBuilder(types.Address.Builder value) {
      clearCustomerAddress();
      customer_addressBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'customer_address' field has an active Builder instance
     * @return True if the 'customer_address' field has an active Builder instance
     */
    public boolean hasCustomerAddressBuilder() {
      return customer_addressBuilder != null;
    }

    /**
      * Clears the value of the 'customer_address' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearCustomerAddress() {
      customer_address = null;
      customer_addressBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'is_logged_in' field.
      * @return The value.
      */
    public java.lang.Boolean getIsLoggedIn() {
      return is_logged_in;
    }

    /**
      * Sets the value of the 'is_logged_in' field.
      * @param value The value of 'is_logged_in'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setIsLoggedIn(java.lang.Boolean value) {
      validate(fields()[3], value);
      this.is_logged_in = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'is_logged_in' field has been set.
      * @return True if the 'is_logged_in' field has been set, false otherwise.
      */
    public boolean hasIsLoggedIn() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'is_logged_in' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearIsLoggedIn() {
      is_logged_in = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'cookie_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getCookieId() {
      return cookie_id;
    }

    /**
      * Sets the value of the 'cookie_id' field.
      * @param value The value of 'cookie_id'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setCookieId(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.cookie_id = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'cookie_id' field has been set.
      * @return True if the 'cookie_id' field has been set, false otherwise.
      */
    public boolean hasCookieId() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'cookie_id' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearCookieId() {
      cookie_id = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'card_no' field.
      * @return The value.
      */
    public java.lang.Long getCardNo() {
      return card_no;
    }

    /**
      * Sets the value of the 'card_no' field.
      * @param value The value of 'card_no'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setCardNo(java.lang.Long value) {
      validate(fields()[5], value);
      this.card_no = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'card_no' field has been set.
      * @return True if the 'card_no' field has been set, false otherwise.
      */
    public boolean hasCardNo() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'card_no' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearCardNo() {
      card_no = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'txn_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getTxnId() {
      return txn_id;
    }

    /**
      * Sets the value of the 'txn_id' field.
      * @param value The value of 'txn_id'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setTxnId(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.txn_id = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'txn_id' field has been set.
      * @return True if the 'txn_id' field has been set, false otherwise.
      */
    public boolean hasTxnId() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'txn_id' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearTxnId() {
      txn_id = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'txn_amount' field.
      * @return The value.
      */
    public java.lang.Double getTxnAmount() {
      return txn_amount;
    }

    /**
      * Sets the value of the 'txn_amount' field.
      * @param value The value of 'txn_amount'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setTxnAmount(java.lang.Double value) {
      validate(fields()[7], value);
      this.txn_amount = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'txn_amount' field has been set.
      * @return True if the 'txn_amount' field has been set, false otherwise.
      */
    public boolean hasTxnAmount() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'txn_amount' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearTxnAmount() {
      txn_amount = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'merchant_id' field.
      * @return The value.
      */
    public java.lang.CharSequence getMerchantId() {
      return merchant_id;
    }

    /**
      * Sets the value of the 'merchant_id' field.
      * @param value The value of 'merchant_id'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setMerchantId(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.merchant_id = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_id' field has been set.
      * @return True if the 'merchant_id' field has been set, false otherwise.
      */
    public boolean hasMerchantId() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'merchant_id' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearMerchantId() {
      merchant_id = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'merchant_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getMerchantName() {
      return merchant_name;
    }

    /**
      * Sets the value of the 'merchant_name' field.
      * @param value The value of 'merchant_name'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setMerchantName(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.merchant_name = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_name' field has been set.
      * @return True if the 'merchant_name' field has been set, false otherwise.
      */
    public boolean hasMerchantName() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'merchant_name' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearMerchantName() {
      merchant_name = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'merchant_address' field.
      * @return The value.
      */
    public types.Address getMerchantAddress() {
      return merchant_address;
    }

    /**
      * Sets the value of the 'merchant_address' field.
      * @param value The value of 'merchant_address'.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder setMerchantAddress(types.Address value) {
      validate(fields()[10], value);
      this.merchant_addressBuilder = null;
      this.merchant_address = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_address' field has been set.
      * @return True if the 'merchant_address' field has been set, false otherwise.
      */
    public boolean hasMerchantAddress() {
      return fieldSetFlags()[10];
    }

    /**
     * Gets the Builder instance for the 'merchant_address' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public types.Address.Builder getMerchantAddressBuilder() {
      if (merchant_addressBuilder == null) {
        if (hasMerchantAddress()) {
          setMerchantAddressBuilder(types.Address.newBuilder(merchant_address));
        } else {
          setMerchantAddressBuilder(types.Address.newBuilder());
        }
      }
      return merchant_addressBuilder;
    }

    /**
     * Sets the Builder instance for the 'merchant_address' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public types.CreditCardTxn.Builder setMerchantAddressBuilder(types.Address.Builder value) {
      clearMerchantAddress();
      merchant_addressBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'merchant_address' field has an active Builder instance
     * @return True if the 'merchant_address' field has an active Builder instance
     */
    public boolean hasMerchantAddressBuilder() {
      return merchant_addressBuilder != null;
    }

    /**
      * Clears the value of the 'merchant_address' field.
      * @return This builder.
      */
    public types.CreditCardTxn.Builder clearMerchantAddress() {
      merchant_address = null;
      merchant_addressBuilder = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    public CreditCardTxn build() {
      try {
        CreditCardTxn record = new CreditCardTxn();
        record.customer_id = fieldSetFlags()[0] ? this.customer_id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.customer_type = fieldSetFlags()[1] ? this.customer_type : (java.lang.CharSequence) defaultValue(fields()[1]);
        if (customer_addressBuilder != null) {
          record.customer_address = this.customer_addressBuilder.build();
        } else {
          record.customer_address = fieldSetFlags()[2] ? this.customer_address : (types.Address) defaultValue(fields()[2]);
        }
        record.is_logged_in = fieldSetFlags()[3] ? this.is_logged_in : (java.lang.Boolean) defaultValue(fields()[3]);
        record.cookie_id = fieldSetFlags()[4] ? this.cookie_id : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.card_no = fieldSetFlags()[5] ? this.card_no : (java.lang.Long) defaultValue(fields()[5]);
        record.txn_id = fieldSetFlags()[6] ? this.txn_id : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.txn_amount = fieldSetFlags()[7] ? this.txn_amount : (java.lang.Double) defaultValue(fields()[7]);
        record.merchant_id = fieldSetFlags()[8] ? this.merchant_id : (java.lang.CharSequence) defaultValue(fields()[8]);
        record.merchant_name = fieldSetFlags()[9] ? this.merchant_name : (java.lang.CharSequence) defaultValue(fields()[9]);
        if (merchant_addressBuilder != null) {
          record.merchant_address = this.merchant_addressBuilder.build();
        } else {
          record.merchant_address = fieldSetFlags()[10] ? this.merchant_address : (types.Address) defaultValue(fields()[10]);
        }
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
