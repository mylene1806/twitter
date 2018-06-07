package corba;


/**
* corba/ServiceCorbaHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from twitter.idl
* jeudi 7 juin 2018 17 h 30 CEST
*/

abstract public class ServiceCorbaHelper
{
  private static String  _id = "IDL:corba/ServiceCorba:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.ServiceCorba that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.ServiceCorba extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corba.ServiceCorbaHelper.id (), "ServiceCorba");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corba.ServiceCorba read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ServiceCorbaStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.ServiceCorba value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corba.ServiceCorba narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.ServiceCorba)
      return (corba.ServiceCorba)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._ServiceCorbaStub stub = new corba._ServiceCorbaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corba.ServiceCorba unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.ServiceCorba)
      return (corba.ServiceCorba)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._ServiceCorbaStub stub = new corba._ServiceCorbaStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
