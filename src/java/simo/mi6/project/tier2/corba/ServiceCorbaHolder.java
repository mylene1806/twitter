package corba;

/**
* corba/ServiceCorbaHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from twitter.idl
* jeudi 7 juin 2018 16 h 21 CEST
*/

public final class ServiceCorbaHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.ServiceCorba value = null;

  public ServiceCorbaHolder ()
  {
  }

  public ServiceCorbaHolder (corba.ServiceCorba initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.ServiceCorbaHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.ServiceCorbaHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.ServiceCorbaHelper.type ();
  }

}
