package corba;


/**
* corba/_ServiceCorbaStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from twitter.idl
* jeudi 7 juin 2018 17 h 30 CEST
*/

public class _ServiceCorbaStub extends org.omg.CORBA.portable.ObjectImpl implements corba.ServiceCorba
{

  public String[] getUsers ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUsers", true);
                $in = _invoke ($out);
                String $result[] = corba.UsersHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUsers (        );
            } finally {
                _releaseReply ($in);
            }
  } // getUsers

  public void createNewUser (String username, String password)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createNewUser", true);
                $out.write_string (username);
                $out.write_string (password);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createNewUser (username, password        );
            } finally {
                _releaseReply ($in);
            }
  } // createNewUser

  public String isUserPasswordCorrect (String username, String password)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("isUserPasswordCorrect", true);
                $out.write_string (username);
                $out.write_string (password);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return isUserPasswordCorrect (username, password        );
            } finally {
                _releaseReply ($in);
            }
  } // isUserPasswordCorrect

  public void removeUser (String u)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("removeUser", true);
                corba.UserHelper.write ($out, u);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                removeUser (u        );
            } finally {
                _releaseReply ($in);
            }
  } // removeUser

  public String[] getUsersFollowing (String u)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUsersFollowing", true);
                corba.UserHelper.write ($out, u);
                $in = _invoke ($out);
                String $result[] = corba.UsersHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUsersFollowing (u        );
            } finally {
                _releaseReply ($in);
            }
  } // getUsersFollowing

  public String[] getUsersFollowedBy (String u)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUsersFollowedBy", true);
                corba.UserHelper.write ($out, u);
                $in = _invoke ($out);
                String $result[] = corba.UsersHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUsersFollowedBy (u        );
            } finally {
                _releaseReply ($in);
            }
  } // getUsersFollowedBy

  public void startFollowing (String follower, String followed)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startFollowing", true);
                $out.write_string (follower);
                $out.write_string (followed);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                startFollowing (follower, followed        );
            } finally {
                _releaseReply ($in);
            }
  } // startFollowing

  public void stopFollowing (String follower, String followed)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("stopFollowing", true);
                $out.write_string (follower);
                $out.write_string (followed);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                stopFollowing (follower, followed        );
            } finally {
                _releaseReply ($in);
            }
  } // stopFollowing

  public void createNewTweet (String username, String t)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createNewTweet", true);
                $out.write_string (username);
                corba.TweetHelper.write ($out, t);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createNewTweet (username, t        );
            } finally {
                _releaseReply ($in);
            }
  } // createNewTweet

  public String[] getTweetsOfUser (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTweetsOfUser", true);
                $out.write_string (username);
                $in = _invoke ($out);
                String $result[] = corba.TweetsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTweetsOfUser (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getTweetsOfUser

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/ServiceCorba:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ServiceCorbaStub
