/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package types;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class NotloggedIn extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7198323584901216153L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NotloggedIn\",\"namespace\":\"types\",\"fields\":[{\"name\":\"card_no\",\"type\":[\"null\",\"long\"]},{\"name\":\"txn_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"txn_amount\",\"type\":[\"null\",\"double\"]},{\"name\":\"merchant_id\",\"type\":[\"null\",\"string\"]},{\"name\":\"merchant_name\",\"type\":[\"null\",\"string\"]},{\"name\":\"merchant_address\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Address\",\"fields\":[{\"name\":\"AddressLine\",\"type\":[\"null\",\"string\"]},{\"name\":\"City\",\"type\":[\"null\",\"string\"]},{\"name\":\"State\",\"type\":[\"null\",\"string\"]},{\"name\":\"PinCode\",\"type\":[\"null\",\"string\"]},{\"name\":\"ContactNumber\",\"type\":[\"null\",\"string\"]}]}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
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
  public NotloggedIn() {}

  /**
   * All-args constructor.
   * @param card_no The new value for card_no
   * @param txn_id The new value for txn_id
   * @param txn_amount The new value for txn_amount
   * @param merchant_id The new value for merchant_id
   * @param merchant_name The new value for merchant_name
   * @param merchant_address The new value for merchant_address
   */
  public NotloggedIn(java.lang.Long card_no, java.lang.CharSequence txn_id, java.lang.Double txn_amount, java.lang.CharSequence merchant_id, java.lang.CharSequence merchant_name, types.Address merchant_address) {
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
    case 0: return card_no;
    case 1: return txn_id;
    case 2: return txn_amount;
    case 3: return merchant_id;
    case 4: return merchant_name;
    case 5: return merchant_address;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: card_no = (java.lang.Long)value$; break;
    case 1: txn_id = (java.lang.CharSequence)value$; break;
    case 2: txn_amount = (java.lang.Double)value$; break;
    case 3: merchant_id = (java.lang.CharSequence)value$; break;
    case 4: merchant_name = (java.lang.CharSequence)value$; break;
    case 5: merchant_address = (types.Address)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
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
   * Creates a new NotloggedIn RecordBuilder.
   * @return A new NotloggedIn RecordBuilder
   */
  public static types.NotloggedIn.Builder newBuilder() {
    return new types.NotloggedIn.Builder();
  }

  /**
   * Creates a new NotloggedIn RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NotloggedIn RecordBuilder
   */
  public static types.NotloggedIn.Builder newBuilder(types.NotloggedIn.Builder other) {
    return new types.NotloggedIn.Builder(other);
  }

  /**
   * Creates a new NotloggedIn RecordBuilder by copying an existing NotloggedIn instance.
   * @param other The existing instance to copy.
   * @return A new NotloggedIn RecordBuilder
   */
  public static types.NotloggedIn.Builder newBuilder(types.NotloggedIn other) {
    return new types.NotloggedIn.Builder(other);
  }

  /**
   * RecordBuilder for NotloggedIn instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NotloggedIn>
    implements org.apache.avro.data.RecordBuilder<NotloggedIn> {

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
    private Builder(types.NotloggedIn.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.card_no)) {
        this.card_no = data().deepCopy(fields()[0].schema(), other.card_no);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.txn_id)) {
        this.txn_id = data().deepCopy(fields()[1].schema(), other.txn_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.txn_amount)) {
        this.txn_amount = data().deepCopy(fields()[2].schema(), other.txn_amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.merchant_id)) {
        this.merchant_id = data().deepCopy(fields()[3].schema(), other.merchant_id);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.merchant_name)) {
        this.merchant_name = data().deepCopy(fields()[4].schema(), other.merchant_name);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.merchant_address)) {
        this.merchant_address = data().deepCopy(fields()[5].schema(), other.merchant_address);
        fieldSetFlags()[5] = true;
      }
      if (other.hasMerchantAddressBuilder()) {
        this.merchant_addressBuilder = types.Address.newBuilder(other.getMerchantAddressBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing NotloggedIn instance
     * @param other The existing instance to copy.
     */
    private Builder(types.NotloggedIn other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.card_no)) {
        this.card_no = data().deepCopy(fields()[0].schema(), other.card_no);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.txn_id)) {
        this.txn_id = data().deepCopy(fields()[1].schema(), other.txn_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.txn_amount)) {
        this.txn_amount = data().deepCopy(fields()[2].schema(), other.txn_amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.merchant_id)) {
        this.merchant_id = data().deepCopy(fields()[3].schema(), other.merchant_id);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.merchant_name)) {
        this.merchant_name = data().deepCopy(fields()[4].schema(), other.merchant_name);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.merchant_address)) {
        this.merchant_address = data().deepCopy(fields()[5].schema(), other.merchant_address);
        fieldSetFlags()[5] = true;
      }
      this.merchant_addressBuilder = null;
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
    public types.NotloggedIn.Builder setCardNo(java.lang.Long value) {
      validate(fields()[0], value);
      this.card_no = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'card_no' field has been set.
      * @return True if the 'card_no' field has been set, false otherwise.
      */
    public boolean hasCardNo() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'card_no' field.
      * @return This builder.
      */
    public types.NotloggedIn.Builder clearCardNo() {
      card_no = null;
      fieldSetFlags()[0] = false;
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
    public types.NotloggedIn.Builder setTxnId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.txn_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'txn_id' field has been set.
      * @return True if the 'txn_id' field has been set, false otherwise.
      */
    public boolean hasTxnId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'txn_id' field.
      * @return This builder.
      */
    public types.NotloggedIn.Builder clearTxnId() {
      txn_id = null;
      fieldSetFlags()[1] = false;
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
    public types.NotloggedIn.Builder setTxnAmount(java.lang.Double value) {
      validate(fields()[2], value);
      this.txn_amount = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'txn_amount' field has been set.
      * @return True if the 'txn_amount' field has been set, false otherwise.
      */
    public boolean hasTxnAmount() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'txn_amount' field.
      * @return This builder.
      */
    public types.NotloggedIn.Builder clearTxnAmount() {
      txn_amount = null;
      fieldSetFlags()[2] = false;
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
    public types.NotloggedIn.Builder setMerchantId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.merchant_id = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_id' field has been set.
      * @return True if the 'merchant_id' field has been set, false otherwise.
      */
    public boolean hasMerchantId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'merchant_id' field.
      * @return This builder.
      */
    public types.NotloggedIn.Builder clearMerchantId() {
      merchant_id = null;
      fieldSetFlags()[3] = false;
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
    public types.NotloggedIn.Builder setMerchantName(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.merchant_name = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_name' field has been set.
      * @return True if the 'merchant_name' field has been set, false otherwise.
      */
    public boolean hasMerchantName() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'merchant_name' field.
      * @return This builder.
      */
    public types.NotloggedIn.Builder clearMerchantName() {
      merchant_name = null;
      fieldSetFlags()[4] = false;
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
    public types.NotloggedIn.Builder setMerchantAddress(types.Address value) {
      validate(fields()[5], value);
      this.merchant_addressBuilder = null;
      this.merchant_address = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'merchant_address' field has been set.
      * @return True if the 'merchant_address' field has been set, false otherwise.
      */
    public boolean hasMerchantAddress() {
      return fieldSetFlags()[5];
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
    public types.NotloggedIn.Builder setMerchantAddressBuilder(types.Address.Builder value) {
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
    public types.NotloggedIn.Builder clearMerchantAddress() {
      merchant_address = null;
      merchant_addressBuilder = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public NotloggedIn build() {
      try {
        NotloggedIn record = new NotloggedIn();
        record.card_no = fieldSetFlags()[0] ? this.card_no : (java.lang.Long) defaultValue(fields()[0]);
        record.txn_id = fieldSetFlags()[1] ? this.txn_id : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.txn_amount = fieldSetFlags()[2] ? this.txn_amount : (java.lang.Double) defaultValue(fields()[2]);
        record.merchant_id = fieldSetFlags()[3] ? this.merchant_id : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.merchant_name = fieldSetFlags()[4] ? this.merchant_name : (java.lang.CharSequence) defaultValue(fields()[4]);
        if (merchant_addressBuilder != null) {
          record.merchant_address = this.merchant_addressBuilder.build();
        } else {
          record.merchant_address = fieldSetFlags()[5] ? this.merchant_address : (types.Address) defaultValue(fields()[5]);
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
