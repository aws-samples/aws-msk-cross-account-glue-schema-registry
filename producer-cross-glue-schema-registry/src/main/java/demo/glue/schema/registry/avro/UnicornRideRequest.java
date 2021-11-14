/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package demo.glue.schema.registry.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class UnicornRideRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7071685435420298014L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UnicornRideRequest\",\"namespace\":\"demo.glue.schema.registry.avro\",\"fields\":[{\"name\":\"request_id\",\"type\":\"int\",\"doc\":\"customer request id\"},{\"name\":\"pickup_address\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"customer pickup address\"},{\"name\":\"destination_address\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"customer destination address\"},{\"name\":\"ride_fare\",\"type\":\"float\",\"doc\":\"ride fare amount (USD)\"},{\"name\":\"ride_duration\",\"type\":\"int\",\"doc\":\"ride duration in minutes\"},{\"name\":\"preferred_unicorn_color\",\"type\":{\"type\":\"enum\",\"name\":\"UnicornPreferredColor\",\"symbols\":[\"WHITE\",\"BLACK\",\"RED\",\"BLUE\",\"GREY\"]},\"default\":\"WHITE\"},{\"name\":\"recommended_unicorn\",\"type\":{\"type\":\"record\",\"name\":\"RecommendedUnicorn\",\"fields\":[{\"name\":\"unicorn_id\",\"type\":\"int\",\"doc\":\"recommended unicorn id\"},{\"name\":\"color\",\"type\":{\"type\":\"enum\",\"name\":\"unicorn_color\",\"symbols\":[\"WHITE\",\"RED\",\"BLUE\"]}},{\"name\":\"stars_rating\",\"type\":[\"null\",\"int\"],\"doc\":\"unicorn star ratings based on customers feedback\",\"default\":null}]}},{\"name\":\"customer\",\"type\":{\"type\":\"record\",\"name\":\"Customer\",\"fields\":[{\"name\":\"customer_account_no\",\"type\":\"int\",\"doc\":\"customer account number\"},{\"name\":\"first_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"middle_name\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"last_name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email_addresses\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"name\":\"customer_address\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"customer address\"},{\"name\":\"mode_of_payment\",\"type\":{\"type\":\"enum\",\"name\":\"ModeOfPayment\",\"symbols\":[\"CARD\",\"CASH\"]},\"default\":\"CARD\"},{\"name\":\"customer_rating\",\"type\":[\"null\",\"int\"],\"default\":null}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<UnicornRideRequest> ENCODER =
      new BinaryMessageEncoder<UnicornRideRequest>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UnicornRideRequest> DECODER =
      new BinaryMessageDecoder<UnicornRideRequest>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<UnicornRideRequest> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<UnicornRideRequest> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<UnicornRideRequest> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<UnicornRideRequest>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this UnicornRideRequest to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a UnicornRideRequest from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a UnicornRideRequest instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static UnicornRideRequest fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** customer request id */
   private int request_id;
  /** customer pickup address */
   private java.lang.String pickup_address;
  /** customer destination address */
   private java.lang.String destination_address;
  /** ride fare amount (USD) */
   private float ride_fare;
  /** ride duration in minutes */
   private int ride_duration;
   private demo.glue.schema.registry.avro.UnicornPreferredColor preferred_unicorn_color;
   private demo.glue.schema.registry.avro.RecommendedUnicorn recommended_unicorn;
   private demo.glue.schema.registry.avro.Customer customer;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UnicornRideRequest() {}

  /**
   * All-args constructor.
   * @param request_id customer request id
   * @param pickup_address customer pickup address
   * @param destination_address customer destination address
   * @param ride_fare ride fare amount (USD)
   * @param ride_duration ride duration in minutes
   * @param preferred_unicorn_color The new value for preferred_unicorn_color
   * @param recommended_unicorn The new value for recommended_unicorn
   * @param customer The new value for customer
   */
  public UnicornRideRequest(java.lang.Integer request_id, java.lang.String pickup_address, java.lang.String destination_address, java.lang.Float ride_fare, java.lang.Integer ride_duration, demo.glue.schema.registry.avro.UnicornPreferredColor preferred_unicorn_color, demo.glue.schema.registry.avro.RecommendedUnicorn recommended_unicorn, demo.glue.schema.registry.avro.Customer customer) {
    this.request_id = request_id;
    this.pickup_address = pickup_address;
    this.destination_address = destination_address;
    this.ride_fare = ride_fare;
    this.ride_duration = ride_duration;
    this.preferred_unicorn_color = preferred_unicorn_color;
    this.recommended_unicorn = recommended_unicorn;
    this.customer = customer;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return request_id;
    case 1: return pickup_address;
    case 2: return destination_address;
    case 3: return ride_fare;
    case 4: return ride_duration;
    case 5: return preferred_unicorn_color;
    case 6: return recommended_unicorn;
    case 7: return customer;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: request_id = (java.lang.Integer)value$; break;
    case 1: pickup_address = value$ != null ? value$.toString() : null; break;
    case 2: destination_address = value$ != null ? value$.toString() : null; break;
    case 3: ride_fare = (java.lang.Float)value$; break;
    case 4: ride_duration = (java.lang.Integer)value$; break;
    case 5: preferred_unicorn_color = (demo.glue.schema.registry.avro.UnicornPreferredColor)value$; break;
    case 6: recommended_unicorn = (demo.glue.schema.registry.avro.RecommendedUnicorn)value$; break;
    case 7: customer = (demo.glue.schema.registry.avro.Customer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'request_id' field.
   * @return customer request id
   */
  public int getRequestId() {
    return request_id;
  }


  /**
   * Sets the value of the 'request_id' field.
   * customer request id
   * @param value the value to set.
   */
  public void setRequestId(int value) {
    this.request_id = value;
  }

  /**
   * Gets the value of the 'pickup_address' field.
   * @return customer pickup address
   */
  public java.lang.String getPickupAddress() {
    return pickup_address;
  }


  /**
   * Sets the value of the 'pickup_address' field.
   * customer pickup address
   * @param value the value to set.
   */
  public void setPickupAddress(java.lang.String value) {
    this.pickup_address = value;
  }

  /**
   * Gets the value of the 'destination_address' field.
   * @return customer destination address
   */
  public java.lang.String getDestinationAddress() {
    return destination_address;
  }


  /**
   * Sets the value of the 'destination_address' field.
   * customer destination address
   * @param value the value to set.
   */
  public void setDestinationAddress(java.lang.String value) {
    this.destination_address = value;
  }

  /**
   * Gets the value of the 'ride_fare' field.
   * @return ride fare amount (USD)
   */
  public float getRideFare() {
    return ride_fare;
  }


  /**
   * Sets the value of the 'ride_fare' field.
   * ride fare amount (USD)
   * @param value the value to set.
   */
  public void setRideFare(float value) {
    this.ride_fare = value;
  }

  /**
   * Gets the value of the 'ride_duration' field.
   * @return ride duration in minutes
   */
  public int getRideDuration() {
    return ride_duration;
  }


  /**
   * Sets the value of the 'ride_duration' field.
   * ride duration in minutes
   * @param value the value to set.
   */
  public void setRideDuration(int value) {
    this.ride_duration = value;
  }

  /**
   * Gets the value of the 'preferred_unicorn_color' field.
   * @return The value of the 'preferred_unicorn_color' field.
   */
  public demo.glue.schema.registry.avro.UnicornPreferredColor getPreferredUnicornColor() {
    return preferred_unicorn_color;
  }


  /**
   * Sets the value of the 'preferred_unicorn_color' field.
   * @param value the value to set.
   */
  public void setPreferredUnicornColor(demo.glue.schema.registry.avro.UnicornPreferredColor value) {
    this.preferred_unicorn_color = value;
  }

  /**
   * Gets the value of the 'recommended_unicorn' field.
   * @return The value of the 'recommended_unicorn' field.
   */
  public demo.glue.schema.registry.avro.RecommendedUnicorn getRecommendedUnicorn() {
    return recommended_unicorn;
  }


  /**
   * Sets the value of the 'recommended_unicorn' field.
   * @param value the value to set.
   */
  public void setRecommendedUnicorn(demo.glue.schema.registry.avro.RecommendedUnicorn value) {
    this.recommended_unicorn = value;
  }

  /**
   * Gets the value of the 'customer' field.
   * @return The value of the 'customer' field.
   */
  public demo.glue.schema.registry.avro.Customer getCustomer() {
    return customer;
  }


  /**
   * Sets the value of the 'customer' field.
   * @param value the value to set.
   */
  public void setCustomer(demo.glue.schema.registry.avro.Customer value) {
    this.customer = value;
  }

  /**
   * Creates a new UnicornRideRequest RecordBuilder.
   * @return A new UnicornRideRequest RecordBuilder
   */
  public static demo.glue.schema.registry.avro.UnicornRideRequest.Builder newBuilder() {
    return new demo.glue.schema.registry.avro.UnicornRideRequest.Builder();
  }

  /**
   * Creates a new UnicornRideRequest RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UnicornRideRequest RecordBuilder
   */
  public static demo.glue.schema.registry.avro.UnicornRideRequest.Builder newBuilder(demo.glue.schema.registry.avro.UnicornRideRequest.Builder other) {
    if (other == null) {
      return new demo.glue.schema.registry.avro.UnicornRideRequest.Builder();
    } else {
      return new demo.glue.schema.registry.avro.UnicornRideRequest.Builder(other);
    }
  }

  /**
   * Creates a new UnicornRideRequest RecordBuilder by copying an existing UnicornRideRequest instance.
   * @param other The existing instance to copy.
   * @return A new UnicornRideRequest RecordBuilder
   */
  public static demo.glue.schema.registry.avro.UnicornRideRequest.Builder newBuilder(demo.glue.schema.registry.avro.UnicornRideRequest other) {
    if (other == null) {
      return new demo.glue.schema.registry.avro.UnicornRideRequest.Builder();
    } else {
      return new demo.glue.schema.registry.avro.UnicornRideRequest.Builder(other);
    }
  }

  /**
   * RecordBuilder for UnicornRideRequest instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UnicornRideRequest>
    implements org.apache.avro.data.RecordBuilder<UnicornRideRequest> {

    /** customer request id */
    private int request_id;
    /** customer pickup address */
    private java.lang.String pickup_address;
    /** customer destination address */
    private java.lang.String destination_address;
    /** ride fare amount (USD) */
    private float ride_fare;
    /** ride duration in minutes */
    private int ride_duration;
    private demo.glue.schema.registry.avro.UnicornPreferredColor preferred_unicorn_color;
    private demo.glue.schema.registry.avro.RecommendedUnicorn recommended_unicorn;
    private demo.glue.schema.registry.avro.RecommendedUnicorn.Builder recommended_unicornBuilder;
    private demo.glue.schema.registry.avro.Customer customer;
    private demo.glue.schema.registry.avro.Customer.Builder customerBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(demo.glue.schema.registry.avro.UnicornRideRequest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.request_id)) {
        this.request_id = data().deepCopy(fields()[0].schema(), other.request_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.pickup_address)) {
        this.pickup_address = data().deepCopy(fields()[1].schema(), other.pickup_address);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.destination_address)) {
        this.destination_address = data().deepCopy(fields()[2].schema(), other.destination_address);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.ride_fare)) {
        this.ride_fare = data().deepCopy(fields()[3].schema(), other.ride_fare);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.ride_duration)) {
        this.ride_duration = data().deepCopy(fields()[4].schema(), other.ride_duration);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.preferred_unicorn_color)) {
        this.preferred_unicorn_color = data().deepCopy(fields()[5].schema(), other.preferred_unicorn_color);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.recommended_unicorn)) {
        this.recommended_unicorn = data().deepCopy(fields()[6].schema(), other.recommended_unicorn);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (other.hasRecommendedUnicornBuilder()) {
        this.recommended_unicornBuilder = demo.glue.schema.registry.avro.RecommendedUnicorn.newBuilder(other.getRecommendedUnicornBuilder());
      }
      if (isValidValue(fields()[7], other.customer)) {
        this.customer = data().deepCopy(fields()[7].schema(), other.customer);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (other.hasCustomerBuilder()) {
        this.customerBuilder = demo.glue.schema.registry.avro.Customer.newBuilder(other.getCustomerBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing UnicornRideRequest instance
     * @param other The existing instance to copy.
     */
    private Builder(demo.glue.schema.registry.avro.UnicornRideRequest other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.request_id)) {
        this.request_id = data().deepCopy(fields()[0].schema(), other.request_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.pickup_address)) {
        this.pickup_address = data().deepCopy(fields()[1].schema(), other.pickup_address);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.destination_address)) {
        this.destination_address = data().deepCopy(fields()[2].schema(), other.destination_address);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.ride_fare)) {
        this.ride_fare = data().deepCopy(fields()[3].schema(), other.ride_fare);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.ride_duration)) {
        this.ride_duration = data().deepCopy(fields()[4].schema(), other.ride_duration);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.preferred_unicorn_color)) {
        this.preferred_unicorn_color = data().deepCopy(fields()[5].schema(), other.preferred_unicorn_color);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.recommended_unicorn)) {
        this.recommended_unicorn = data().deepCopy(fields()[6].schema(), other.recommended_unicorn);
        fieldSetFlags()[6] = true;
      }
      this.recommended_unicornBuilder = null;
      if (isValidValue(fields()[7], other.customer)) {
        this.customer = data().deepCopy(fields()[7].schema(), other.customer);
        fieldSetFlags()[7] = true;
      }
      this.customerBuilder = null;
    }

    /**
      * Gets the value of the 'request_id' field.
      * customer request id
      * @return The value.
      */
    public int getRequestId() {
      return request_id;
    }


    /**
      * Sets the value of the 'request_id' field.
      * customer request id
      * @param value The value of 'request_id'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setRequestId(int value) {
      validate(fields()[0], value);
      this.request_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'request_id' field has been set.
      * customer request id
      * @return True if the 'request_id' field has been set, false otherwise.
      */
    public boolean hasRequestId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'request_id' field.
      * customer request id
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearRequestId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'pickup_address' field.
      * customer pickup address
      * @return The value.
      */
    public java.lang.String getPickupAddress() {
      return pickup_address;
    }


    /**
      * Sets the value of the 'pickup_address' field.
      * customer pickup address
      * @param value The value of 'pickup_address'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setPickupAddress(java.lang.String value) {
      validate(fields()[1], value);
      this.pickup_address = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'pickup_address' field has been set.
      * customer pickup address
      * @return True if the 'pickup_address' field has been set, false otherwise.
      */
    public boolean hasPickupAddress() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'pickup_address' field.
      * customer pickup address
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearPickupAddress() {
      pickup_address = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'destination_address' field.
      * customer destination address
      * @return The value.
      */
    public java.lang.String getDestinationAddress() {
      return destination_address;
    }


    /**
      * Sets the value of the 'destination_address' field.
      * customer destination address
      * @param value The value of 'destination_address'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setDestinationAddress(java.lang.String value) {
      validate(fields()[2], value);
      this.destination_address = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'destination_address' field has been set.
      * customer destination address
      * @return True if the 'destination_address' field has been set, false otherwise.
      */
    public boolean hasDestinationAddress() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'destination_address' field.
      * customer destination address
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearDestinationAddress() {
      destination_address = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'ride_fare' field.
      * ride fare amount (USD)
      * @return The value.
      */
    public float getRideFare() {
      return ride_fare;
    }


    /**
      * Sets the value of the 'ride_fare' field.
      * ride fare amount (USD)
      * @param value The value of 'ride_fare'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setRideFare(float value) {
      validate(fields()[3], value);
      this.ride_fare = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'ride_fare' field has been set.
      * ride fare amount (USD)
      * @return True if the 'ride_fare' field has been set, false otherwise.
      */
    public boolean hasRideFare() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'ride_fare' field.
      * ride fare amount (USD)
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearRideFare() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'ride_duration' field.
      * ride duration in minutes
      * @return The value.
      */
    public int getRideDuration() {
      return ride_duration;
    }


    /**
      * Sets the value of the 'ride_duration' field.
      * ride duration in minutes
      * @param value The value of 'ride_duration'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setRideDuration(int value) {
      validate(fields()[4], value);
      this.ride_duration = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'ride_duration' field has been set.
      * ride duration in minutes
      * @return True if the 'ride_duration' field has been set, false otherwise.
      */
    public boolean hasRideDuration() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'ride_duration' field.
      * ride duration in minutes
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearRideDuration() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'preferred_unicorn_color' field.
      * @return The value.
      */
    public demo.glue.schema.registry.avro.UnicornPreferredColor getPreferredUnicornColor() {
      return preferred_unicorn_color;
    }


    /**
      * Sets the value of the 'preferred_unicorn_color' field.
      * @param value The value of 'preferred_unicorn_color'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setPreferredUnicornColor(demo.glue.schema.registry.avro.UnicornPreferredColor value) {
      validate(fields()[5], value);
      this.preferred_unicorn_color = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'preferred_unicorn_color' field has been set.
      * @return True if the 'preferred_unicorn_color' field has been set, false otherwise.
      */
    public boolean hasPreferredUnicornColor() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'preferred_unicorn_color' field.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearPreferredUnicornColor() {
      preferred_unicorn_color = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'recommended_unicorn' field.
      * @return The value.
      */
    public demo.glue.schema.registry.avro.RecommendedUnicorn getRecommendedUnicorn() {
      return recommended_unicorn;
    }


    /**
      * Sets the value of the 'recommended_unicorn' field.
      * @param value The value of 'recommended_unicorn'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setRecommendedUnicorn(demo.glue.schema.registry.avro.RecommendedUnicorn value) {
      validate(fields()[6], value);
      this.recommended_unicornBuilder = null;
      this.recommended_unicorn = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'recommended_unicorn' field has been set.
      * @return True if the 'recommended_unicorn' field has been set, false otherwise.
      */
    public boolean hasRecommendedUnicorn() {
      return fieldSetFlags()[6];
    }

    /**
     * Gets the Builder instance for the 'recommended_unicorn' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public demo.glue.schema.registry.avro.RecommendedUnicorn.Builder getRecommendedUnicornBuilder() {
      if (recommended_unicornBuilder == null) {
        if (hasRecommendedUnicorn()) {
          setRecommendedUnicornBuilder(demo.glue.schema.registry.avro.RecommendedUnicorn.newBuilder(recommended_unicorn));
        } else {
          setRecommendedUnicornBuilder(demo.glue.schema.registry.avro.RecommendedUnicorn.newBuilder());
        }
      }
      return recommended_unicornBuilder;
    }

    /**
     * Sets the Builder instance for the 'recommended_unicorn' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setRecommendedUnicornBuilder(demo.glue.schema.registry.avro.RecommendedUnicorn.Builder value) {
      clearRecommendedUnicorn();
      recommended_unicornBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'recommended_unicorn' field has an active Builder instance
     * @return True if the 'recommended_unicorn' field has an active Builder instance
     */
    public boolean hasRecommendedUnicornBuilder() {
      return recommended_unicornBuilder != null;
    }

    /**
      * Clears the value of the 'recommended_unicorn' field.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearRecommendedUnicorn() {
      recommended_unicorn = null;
      recommended_unicornBuilder = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'customer' field.
      * @return The value.
      */
    public demo.glue.schema.registry.avro.Customer getCustomer() {
      return customer;
    }


    /**
      * Sets the value of the 'customer' field.
      * @param value The value of 'customer'.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setCustomer(demo.glue.schema.registry.avro.Customer value) {
      validate(fields()[7], value);
      this.customerBuilder = null;
      this.customer = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'customer' field has been set.
      * @return True if the 'customer' field has been set, false otherwise.
      */
    public boolean hasCustomer() {
      return fieldSetFlags()[7];
    }

    /**
     * Gets the Builder instance for the 'customer' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public demo.glue.schema.registry.avro.Customer.Builder getCustomerBuilder() {
      if (customerBuilder == null) {
        if (hasCustomer()) {
          setCustomerBuilder(demo.glue.schema.registry.avro.Customer.newBuilder(customer));
        } else {
          setCustomerBuilder(demo.glue.schema.registry.avro.Customer.newBuilder());
        }
      }
      return customerBuilder;
    }

    /**
     * Sets the Builder instance for the 'customer' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder setCustomerBuilder(demo.glue.schema.registry.avro.Customer.Builder value) {
      clearCustomer();
      customerBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'customer' field has an active Builder instance
     * @return True if the 'customer' field has an active Builder instance
     */
    public boolean hasCustomerBuilder() {
      return customerBuilder != null;
    }

    /**
      * Clears the value of the 'customer' field.
      * @return This builder.
      */
    public demo.glue.schema.registry.avro.UnicornRideRequest.Builder clearCustomer() {
      customer = null;
      customerBuilder = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UnicornRideRequest build() {
      try {
        UnicornRideRequest record = new UnicornRideRequest();
        record.request_id = fieldSetFlags()[0] ? this.request_id : (java.lang.Integer) defaultValue(fields()[0]);
        record.pickup_address = fieldSetFlags()[1] ? this.pickup_address : (java.lang.String) defaultValue(fields()[1]);
        record.destination_address = fieldSetFlags()[2] ? this.destination_address : (java.lang.String) defaultValue(fields()[2]);
        record.ride_fare = fieldSetFlags()[3] ? this.ride_fare : (java.lang.Float) defaultValue(fields()[3]);
        record.ride_duration = fieldSetFlags()[4] ? this.ride_duration : (java.lang.Integer) defaultValue(fields()[4]);
        record.preferred_unicorn_color = fieldSetFlags()[5] ? this.preferred_unicorn_color : (demo.glue.schema.registry.avro.UnicornPreferredColor) defaultValue(fields()[5]);
        if (recommended_unicornBuilder != null) {
          try {
            record.recommended_unicorn = this.recommended_unicornBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("recommended_unicorn"));
            throw e;
          }
        } else {
          record.recommended_unicorn = fieldSetFlags()[6] ? this.recommended_unicorn : (demo.glue.schema.registry.avro.RecommendedUnicorn) defaultValue(fields()[6]);
        }
        if (customerBuilder != null) {
          try {
            record.customer = this.customerBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("customer"));
            throw e;
          }
        } else {
          record.customer = fieldSetFlags()[7] ? this.customer : (demo.glue.schema.registry.avro.Customer) defaultValue(fields()[7]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UnicornRideRequest>
    WRITER$ = (org.apache.avro.io.DatumWriter<UnicornRideRequest>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UnicornRideRequest>
    READER$ = (org.apache.avro.io.DatumReader<UnicornRideRequest>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeInt(this.request_id);

    out.writeString(this.pickup_address);

    out.writeString(this.destination_address);

    out.writeFloat(this.ride_fare);

    out.writeInt(this.ride_duration);

    out.writeEnum(this.preferred_unicorn_color.ordinal());

    this.recommended_unicorn.customEncode(out);

    this.customer.customEncode(out);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.request_id = in.readInt();

      this.pickup_address = in.readString();

      this.destination_address = in.readString();

      this.ride_fare = in.readFloat();

      this.ride_duration = in.readInt();

      this.preferred_unicorn_color = demo.glue.schema.registry.avro.UnicornPreferredColor.values()[in.readEnum()];

      if (this.recommended_unicorn == null) {
        this.recommended_unicorn = new demo.glue.schema.registry.avro.RecommendedUnicorn();
      }
      this.recommended_unicorn.customDecode(in);

      if (this.customer == null) {
        this.customer = new demo.glue.schema.registry.avro.Customer();
      }
      this.customer.customDecode(in);

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.request_id = in.readInt();
          break;

        case 1:
          this.pickup_address = in.readString();
          break;

        case 2:
          this.destination_address = in.readString();
          break;

        case 3:
          this.ride_fare = in.readFloat();
          break;

        case 4:
          this.ride_duration = in.readInt();
          break;

        case 5:
          this.preferred_unicorn_color = demo.glue.schema.registry.avro.UnicornPreferredColor.values()[in.readEnum()];
          break;

        case 6:
          if (this.recommended_unicorn == null) {
            this.recommended_unicorn = new demo.glue.schema.registry.avro.RecommendedUnicorn();
          }
          this.recommended_unicorn.customDecode(in);
          break;

        case 7:
          if (this.customer == null) {
            this.customer = new demo.glue.schema.registry.avro.Customer();
          }
          this.customer.customDecode(in);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










